const fs = require('fs');
const mkdirp = require('mkdirp');

const words = fs
  .readFileSync(__dirname + '/popular.txt', 'utf8')
  .split('\n');

const services: Service[] = [];

function fmt(n) {
  let result = n.toString();
  while (result.length < 3) {
    result = '0' + result;
  }
  return result;
}

class Service {
  words: string[] = [];
  constructor(public serviceId: number) {}

  get firstPath() {
    return `speller${fmt(this.serviceId)}`;
  }

  get path() {
    return `${this.firstPath}/src/main/java/com/oasisdigital/spelling`;
  }

  writeJava() {
    const java = `package com.oasisdigital.spelling;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import io.grpc.*;
import com.oasisdigital.spelling.api.*;

import java.io.IOException;

public class SpellingServer
    extends SpellCheckerGrpc.SpellCheckerImplBase {
  Set<String> dictionary = new HashSet<String>();
  static int port = 8600 + ${this.serviceId};

  SpellingServer() {
    String words = new String("${this.words.join('|')}");
    for (String word: words.split("\\\\|")) {
      dictionary.add(word);
    }
  }

  public static void main(String[] args) {
    Server server = ServerBuilder.forPort(port)
        .addService(new SpellingServer()).build();
    try {
      server.start();
      System.out.println("Spelling server listening on port " + port);
      server.awaitTermination();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void check(Candidate request,
      io.grpc.stub.StreamObserver<Result> responseObserver) {
    String word = request.getWord();
    boolean found = dictionary.contains(word);
    System.out.println("Server on port " + port + ", " + word + ":"+ found);
    responseObserver.onNext(Result.newBuilder().setOk(found).build());
    responseObserver.onCompleted();
  }
};
`;

    mkdirp.sync(`${this.path}`);
    fs.writeFileSync(`${this.path}/SpellingServer.java`, java);
  }

  writeBuild() {
    const build = `load("@rules_java//java:defs.bzl", "java_library")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "spelling-lib",
    srcs = [
        "SpellingServer.java",
    ],
    deps = [
        "//apidef:api_proto_java",
        "//apidef:api_proto_java_lib",
        "@io_grpc_grpc_java//core",
        "@io_grpc_grpc_java//netty",
        "@io_grpc_grpc_java//stub",
    ],
)
`;

    mkdirp.sync(`${this.path}`);
    fs.writeFileSync(`${this.path}/BUILD.bazel`, build);
  }

  writeAlias() {
    const build = `load("@rules_java//java:defs.bzl", "java_binary")
load("@com_github_atlassian_bazel_tools//multirun:def.bzl", "command")

package(default_visibility = ["//visibility:public"])

java_binary(
  name = "${this.firstPath}",
  main_class = "com.oasisdigital.spelling.SpellingServer",
  runtime_deps = ["//${this.path}:spelling-lib"],
)

command(
  name = "command",
  command = ":${this.firstPath}",
)

`;

    mkdirp.sync(`${this.firstPath}`);
    fs.writeFileSync(`${this.firstPath}/BUILD.bazel`, build);
  }
}

function generateAppModuleBuild() {
  let ts = `SERVICES = [\n`;
  services.forEach((m) => (ts += `    "//${m.firstPath}",\n`));
  ts += `]\n`;

  const buildFile = `service_list.bzl`;
  fs.writeFileSync(buildFile, ts);
}

function generate(nServices: number) {
  for (let m = 0; m < nServices; m++) {
    const service = new Service(m);
    services.push(service);
  }

  words.forEach((word, i) =>
    services[i % services.length].words.push(word)
  );

  services.forEach((m) => m.writeJava());
  services.forEach((m) => m.writeBuild());
  services.forEach((m) => m.writeAlias());

  generateAppModuleBuild();
}

generate(10);
