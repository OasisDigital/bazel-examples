#!/bin/bash
set -e

bazel build '...'

# bazel build --platforms //platforms:a2600 '...'

# Helpful query techniques
# bazel cquery //platforms:a2600 --output starlark --starlark:expr='providers(target)'
#  "PlatformInfo": PlatformInfo(//platforms:a2600, constraints=<[@platforms//os:none]>),
#  "ConstraintValueInfo": ConstraintValueInfo(setting=@platforms//os:os, @platforms//os:linux),
