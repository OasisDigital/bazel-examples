#ifndef INCLUDE_LOOKUP_HPP
#define INCLUDE_LOOKUP_HPP

#include <set>
#include <string>

#include "sqlite3.h"

namespace OasisDigital {

class LookupEngine {
  sqlite3 *Db;
  sqlite3_stmt *AddStatement;
  sqlite3_stmt *CheckStatement;
  void checkSqlite(int rc);

 public:
  LookupEngine(const std::string &FileName);

  virtual ~LookupEngine();

  void AddEntry(const std::string &Word);

  int CheckEntry(const std::string &Word);
};

}  // namespace OasisDigital

#endif
