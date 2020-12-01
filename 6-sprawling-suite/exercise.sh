#!/bin/bash

set -e

bazel build '...' --jobs=2
# bazel test '...' --test_verbose_timeout_warnings --test_output=errors

# bazel run //:service
# bazel run //:frontend

# http://localhost:5432/
# http://localhost:8005/
