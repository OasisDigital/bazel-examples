#include <iostream>
#include <string>

// Bazel recommends that your code refer to each header from the
// workspace root

#include "speller/announce/announce.h"

int main(int argc, char **argv) {
  std::string who = "world";
  if (argc > 1) {
    who = argv[1];
  }

  OasisDigital::announce(who);

  return 0;
}
