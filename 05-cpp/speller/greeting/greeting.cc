#include "speller/greeting/greeting.h"

#include <string>

namespace OasisDigital {

std::string get_greet(const std::string& word) {
  return "Looking up whether '" + word + "' is a valid word";
}

}  // namespace OasisDigital
