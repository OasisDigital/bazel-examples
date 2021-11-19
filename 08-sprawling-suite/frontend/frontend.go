package main

import (
	"context"
	"fmt"
	"log"
	"net/http"
	"strconv"
	"strings"

	"github.com/mattn/go-slim"

	pb "proto/spelling/api"

	"google.golang.org/grpc"
)

var (
	serverAddr = "localhost"
	clients    = []pb.SpellCheckerClient{}
)

func connect() {
	for port := 8599; port < 8610; port++ {
		conn, err := grpc.Dial(
			serverAddr+":"+strconv.Itoa(port),
			grpc.WithInsecure())
		if err != nil {
			log.Fatalf("fail to dial: %v", err)
		}
		client := pb.NewSpellCheckerClient(conn)
		clients = append(clients, client)
	}
}

func main() {
	http.HandleFunc("/", HelloServer)
	fmt.Println("Frontend UI listening on port 8080")
	http.ListenAndServe(":8080", nil)
}

const pageTemplate string = `doctype 5
html lang="en"
  head
    meta charset="UTF-8"
    title
  body
    h1 = word
    h2 = ok
    p Some words to try:
    ul
      li
        a href="?word=berry" berry
      li
        a href="?word=banana" banana
      li
        a href="?word=book" book
      li
        a href="?word=Bazel" Bazel
      li
        a href="?word=Smith" Smith
`

// HelloServer : our entry point.
func HelloServer(w http.ResponseWriter, r *http.Request) {

	if len(clients) == 0 {
		connect()
	}

	query := r.URL.Query()
	words := query["word"]
	word := ""
	ok := false

	if len(words) == 1 {
		word = words[0]
		out1 := make(chan bool)

		for _, cli := range clients {
			// "This behavior of the language, not defining a new variable
			// for each iteration, may have been a mistake in retrospect."
			cli := cli
			go func() {
				result, err := cli.Check(context.Background(), &pb.Candidate{Word: word})
				if err != nil {
					log.Fatalf("Could not retrieve data: %v", err)
					return
				}
				// log.Printf("data: %v", result.GetOk())
				out1 <- result.GetOk()
			}()
		}

		for range clients {
			ok = ok || (<-out1)
		}
	}

	// Prepare a template
	compiledTemplate, err := slim.Parse(strings.NewReader(pageTemplate))
	if err != nil {
		fmt.Fprintf(w, "Failed! %v", err)
		return
	}

	// Use the template to format the data
	err = compiledTemplate.Execute(w, map[string]interface{}{
		"ok":   ok,
		"word": word,
	})
	if err != nil {
		log.Fatalf("Template execution failed: %v", err)
	}
}

// http://localhost:8080/
