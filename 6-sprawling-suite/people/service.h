#ifndef INCLUDE_SERVICE_HPP
#define INCLUDE_SERVICE_HPP

#include <string>

#include <grpcpp/grpcpp.h>
#include "apidef/api.grpc.pb.h"

namespace OasisDigital
{
  class SpellCheckerServiceImpl final : public spelling::SpellChecker::Service
  {
    std::set<std::string> words;

  public:
    SpellCheckerServiceImpl();

    grpc::Status Check(grpc::ServerContext *context, const spelling::Candidate *request,
                       spelling::Result *response);
  };
}

#endif
