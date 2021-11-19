#include "announce.h"

#include <ctime>
#include <iostream>

#include "speller/greeting/greeting.h"

namespace OasisDigital {

void announce(const std::string &who) {
  std::time_t result = std::time(nullptr);
  std::cout << get_greet(who) << std::endl;
  std::cout << "Speller started at "
            << std::asctime(std::localtime(&result)) << std::endl;
}

}  // namespace OasisDigital
