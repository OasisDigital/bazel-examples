#!/bin/bash
set -e

# This script "exercises" various commands that we expect to run
# without error. More typically these might be coded directly in the
# CI setup, but it is helpful to have them in a script for easy local
# development.

bazel build '...'

# Run via Bazel
bazel run //speller/main Beatrice

# Run the output executable
bazel-bin/speller/main/spell Xavier

bazel test //speller/greeting:greeting-test

bazel test --test_output=errors '...'

bazel test '...' --test_verbose_timeout_warnings

# Query examples:

bazel query --notool_deps --noimplicit_deps "deps(//speller/main)" --output graph

# To explore interactively
# apt install graphviz xdot
# brew install graphviz xdot
# xdot <(bazel query --notool_deps --noimplicit_deps "deps(//speller/main)"  --output graph)

# Queries to consider:
# What files depend on external dep X?
# what files are referenced by the integration tests?
