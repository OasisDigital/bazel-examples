#include <iostream>
#include <memory>
#include <string>

#include <grpcpp/grpcpp.h>

#include "service.h"

using grpc::Server;
using grpc::ServerBuilder;

int main(int argc, char **argv)
{
  std::string port = "8599";
  std::string server_address = "0.0.0.0:" + port;
  OasisDigital::SpellCheckerServiceImpl service;

  ServerBuilder builder;
  builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
  builder.RegisterService(&service);
  std::unique_ptr<Server> server(builder.BuildAndStart());
  std::cout << "Proper Name Spelling Server listening on " << server_address << std::endl;
  server->Wait();
  return 0;
}
