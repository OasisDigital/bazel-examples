#!/bin/bash
set -e

export MSYS_NO_PATHCONV=1
export MSYS2_ARG_CONV_EXCL="*"

bazel build '...'

# TODO make these work:
# bazel test '...' --test_verbose_timeout_warnings --test_output=errors

# bazel run //fx-server
# http://localhost:8005/

# bazel run //client
# http://localhost:8080/
