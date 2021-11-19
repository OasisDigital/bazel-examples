#include "speller/greeting/greeting.h"

#include "gtest/gtest.h"

namespace OasisDigital {

TEST(GreetingTest, GetGreet) {
  EXPECT_EQ(get_greet("Bazel"), "Hello Bazel");
}

}  // namespace OasisDigital
