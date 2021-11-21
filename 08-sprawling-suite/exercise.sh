#!/bin/bash
set -e

# Haven't mentioned 'bazel sync' yet; it forced refect of everything
# in the workspace, unconditionally. Useful for doing all the fetch
# and cache population now. It can take a while.

# bazel sync

# While fetch takes a target spec, fetched only what it can find the
# need for.

bazel fetch ...

bazel build '...' --jobs=2
# bazel test '...' --test_verbose_timeout_warnings --test_output=errors

# --keep-going

# TODO update with how to run the other services
# bazel run //people
# bazel run //frontend

# http://localhost:5432/
# http://localhost:8005/
