#!/bin/bash
set -e

bazel build '...'

bazel run //app
