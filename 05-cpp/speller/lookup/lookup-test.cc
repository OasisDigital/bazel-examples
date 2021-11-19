
#include "speller/lookup/lookup.h"

#include <filesystem>
#include <iostream>
#include <string>

#include "gtest/gtest.h"

// Some people advocate keeping GoogleTest tests in the default
// namespace, for shorter test code files and because some test
// machinery expects globaly unique test names anyway.

using namespace OasisDigital;

TEST(LookupEngineTest, CreateAndDestroy) {
  LookupEngine Engine(":memory:");
  // No exception, no problem
}

TEST(LookupEngineTest, PersistsFile) {
  std::string tempFileName = "testdb.db";
  {
    LookupEngine Engine(tempFileName);
    Engine.AddEntry("Bazel");
  }
  std::filesystem::path f{tempFileName};
  EXPECT_TRUE(std::filesystem::exists(f));
  std::filesystem::remove(f);
}

TEST(LookupEngineTest, AddingWords) {
  LookupEngine Engine(":memory:");
  EXPECT_EQ(Engine.CheckEntry("Bazel"), 0);
  Engine.AddEntry("Bazel");
  EXPECT_EQ(Engine.CheckEntry("Bazel"), 1);
}
