#!/bin/bash
set -e

# There are issues running on Bash on windows, some are fixed by this.
export MSYS_NO_PATHCONV=1
export MSYS2_ARG_CONV_EXCL="*"

# You need pip-compile from pip-tools
# python3 -m pip install pip-tools

# Anytime the dependencies change:

(cd app; pip-compile --generate-hashes --output-file=requirements_lock.txt requirements.txt)
bazel run '//app:gazelle_python_manifest.update'

# Anytime the files changes. Consider a pre-commit hook.

bazel run '//:gazelle'
