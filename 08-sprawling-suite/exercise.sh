#!/bin/bash

set -e

bazel build '...' --jobs=2
# bazel test '...' --test_verbose_timeout_warnings --test_output=errors

# --keep-going

# TODO update with how to run the other services
# bazel run //people
# bazel run //frontend

# http://localhost:5432/
# http://localhost:8005/
