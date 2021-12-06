#!/bin/bash
set -e

# There are issues running on Bash on windows, some are fixed by this.
export MSYS_NO_PATHCONV=1
export MSYS2_ARG_CONV_EXCL="*"

bazel build '...'

./gazelle.sh

bazel run //app:app_bin
