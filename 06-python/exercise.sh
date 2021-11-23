#!/bin/bash
set -e

bazel build '...'

./gazelle.sh

bazel run //app:app_bin
