#!/bin/bash

set -e

# Reduce concurrency to avoid failures on low-resource CI machine

bazel build '...' --jobs=2
# bazel test '...' --test_verbose_timeout_warnings --test_output=errors
