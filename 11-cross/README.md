# Cross-compile example

## Tool installation

Ideally we'll build all the tools from source using Bazel, but to keep the focus
on toolchain setup, install them with your OS package manager instead.

```sh
sudo apt install cc65 stella
```

```sh
brew install cc65 stella
```

(Not working yet here:) On windows, download the cc65 precompiled binaries.

https://sourceforge.net/projects/cc65/files/cc65-snapshot-win32.zip

## Build

the old way: make

the new way: bazel build '...'

## Run

Run the make-generated binary:

```sh
stella game/hello
```

```sh
/usr/local/Cellar/stella/6.6/Stella.app/Contents/MacOS/Stella game/hello
```

Run the Bazel-generated binary:

```sh
stella bazel-out/darwin-fastbuild/bin/game/hello
```

```sh
/usr/local/Cellar/stella/6.6/Stella.app/Contents/MacOS/Stella bazel-out/darwin-fastbuild/bin/game/hello
```

## Future enhancement

Consider building the cc65 toolchain hermetically instead of relying
on an OS-level package.

https://github.com/cc65/cc65/archive/refs/heads/master.zip

Consider showing how to adopt an off-the-shelf embedded toolchain,
from this collection:

https://github.com/silvergasp/bazel-embedded

Consider demonstrating this LLVM toolchain:

https://github.com/grailbio/bazel-toolchain
