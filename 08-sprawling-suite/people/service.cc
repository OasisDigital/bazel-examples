#include <grpcpp/grpcpp.h>

#include "apidef/api.grpc.pb.h"

#include "service.h"

using grpc::ServerContext;
using grpc::Status;

using spelling::Candidate;
using spelling::Result;

namespace OasisDigital
{
  SpellCheckerServiceImpl::SpellCheckerServiceImpl()
  {
    words.insert("Fermi");
    words.insert("Heisenberg");
    words.insert("Jones");
    words.insert("Kahn");
    words.insert("Smith");
  }

  Status SpellCheckerServiceImpl::Check(ServerContext *context, const Candidate *request,
                                        Result *response)
  {
    response->set_ok(words.count(request->word()) > 0);
    return Status::OK;
  }
}
