#include "speller/greeting/greeting.h"

#include <string>

namespace OasisDigital {

std::string get_greet(const std::string& who) {
  return "Hello " + who;
}

}
