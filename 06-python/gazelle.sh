#!/bin/bash
set -e

# You need pip-compile from pip-tools
# python3 -m pip install pip-tools

# Anytime the dependencies change:

(cd app; pip-compile --generate-hashes --output-file=requirements_lock.txt requirements.txt)
bazel run //app:gazelle_python_manifest.update

# Anytime the files changes. Consider a pre-commit hook.

bazel run //:gazelle
