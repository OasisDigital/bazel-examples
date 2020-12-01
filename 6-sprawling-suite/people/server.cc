
#include <iostream>
#include <memory>
#include <string>

#include <grpcpp/grpcpp.h>

#include "apidef/api.grpc.pb.h"

using grpc::Server;
using grpc::ServerBuilder;
using grpc::ServerContext;
using grpc::Status;

using spelling::Candidate;
using spelling::Result;
using spelling::SpellChecker;

class SpellCheckerServiceImpl final : public SpellChecker::Service
{
  std::set<std::string> words;

public:
  SpellCheckerServiceImpl()
  {
    words.insert("Smith");
    words.insert("Jones");
    words.insert("Fermi");
    words.insert("Heisenberg");
  }

  Status Check(ServerContext *context, const Candidate *request,
               Result *reply) override
  {
    reply->set_ok(words.count(request->word()) > 0);
    return Status::OK;
  }
};

int main(int argc, char **argv)
{
  std::string port = "8599";
  std::string server_address = "0.0.0.0:" + port;
  SpellCheckerServiceImpl service;

  ServerBuilder builder;
  builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
  builder.RegisterService(&service);
  std::unique_ptr<Server> server(builder.BuildAndStart());
  std::cout << "Proper Name Spelling Server listening on " << server_address << std::endl;
  server->Wait();
  return 0;
}
