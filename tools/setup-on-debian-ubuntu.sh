#!/bin/bash

# Oasis Digital, https://oasisdigital.com
# Kyle Cordes, kyle.cordes@oasisdigital.com

# This is a rough script to set up a Linux machine for use with Bazel.
# Lightly tested. Most people will want something different in some way.

# The "right" way to install Bazel for each platform and sitation is
# still evolving in 2021 as I update this script.

set -e

# Get npm. I use it, and it provide an easy path to install Bazel and
# related tools.

# Set up Node APT repo on Debian.
curl -sL https://deb.nodesource.com/setup_16.x | sudo -E bash -

# Packages helpful for development needs, most not needed for Bazel.

sudo apt update
sudo apt install -y apt-transport-https
sudo apt install -y git nodejs openjdk-11-jdk tig htop parallel pkg-config zip g++ zlib1g-dev unzip python3 graphviz

sudo npm install -g yarn @bazel/bazelisk @bazel/buildifier @bazel/buildozer @bazel/ibazel

# unused-deps is helpful for some Java projects, but doesn't have a
# convenient installer.

# Consider the Bazel installer instead. It sets up Bash completions.
# curl -sSL https://github.com/bazelbuild/bazel/releases/download/1.1.0/bazel-1.1.0-installer-linux-x86_64.sh >bazel_install.sh
# chmod +x bazel_install.sh
# ./bazel_install.sh --user

# TODO:
# test on digital ocean or AWS
