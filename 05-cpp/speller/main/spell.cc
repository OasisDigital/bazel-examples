#include <iostream>
#include <list>
#include <string>

// Bazel recommends that your code refer to each header from the
// workspace root

#include "speller/lookup/lookup.h"

using namespace std;

#include "speller/announce/announce.h"

int main(int argc, char** argv) {
  std::string word = "";
  if (argc > 1) {
    word = argv[1];
  }

  OasisDigital::announce(word);

  string dictionary_file = "spell.db";

  if (const char* env_override = std::getenv("DICTIONARY_FILE")) {
    dictionary_file = env_override;
  }

  OasisDigital::LookupEngine engine(dictionary_file, false);

  if (engine.CheckEntry(word)) {
    cout << "Found it" << endl;
    return 0;
  } else {
    cout << "No such word" << endl;
    return 100;
  }
}
