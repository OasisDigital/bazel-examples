#include "announce.h"

#include <ctime>
#include <iostream>

#include "speller/greeting/greeting.h"

namespace OasisDigital {

void announce(const std::string &word) {
  std::time_t result = std::time(nullptr);
  std::cout << std::endl
            << "Speller executed at "
            << std::asctime(std::localtime(&result));
  std::cout << get_greet(word) << std::endl;
}

}  // namespace OasisDigital
