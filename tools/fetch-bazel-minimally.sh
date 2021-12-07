#!/bin/bash
set -e

# Oasis Digital, https://oasisdigital.com
# Kyle Cordes, kyle.cordes@oasisdigital.com

# Minimal way to get Bazel, without any other package managers. Useful for containers etc.

# Assume Linux x86_64.

cd ~

mkdir -p bin
curl -sSL https://github.com/bazelbuild/bazelisk/releases/download/v1.10.1/bazelisk-linux-amd64 >bin/bazelisk

# The supporting CLI tools are not yet version-synced and released with Bazel, hopefully will happen.

curl -sSL https://github.com/bazelbuild/buildtools/releases/download/4.2.3/buildifier-linux-amd64 >bin/buildifier
curl -sSL https://github.com/bazelbuild/buildtools/releases/download/4.2.3/buildozer-linux-amd64 >bin/buildozer
curl -sSL https://github.com/bazelbuild/buildtools/releases/download/4.2.3/unused_deps-linux-amd64 >bin/unused_deps
curl -sSL https://github.com/bazelbuild/bazel-watcher/releases/download/v0.15.10/ibazel_linux_amd64 >bin/ibazel
cp bin/bazelisk bin/bazel

chmod +x bin/*
