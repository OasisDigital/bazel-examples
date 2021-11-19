#include "lookup.h"

#include <iostream>
#include <string>

namespace OasisDigital {

using namespace std;

void LookupEngine::checkSqlite(int rc) {
  if (rc != SQLITE_OK) {
    std::string msg = "sqlite3 error, ";
    msg.append(sqlite3_errmsg(Db));
    throw runtime_error(msg);
  }
}

LookupEngine::LookupEngine(const std::string &FileName) {
  checkSqlite(sqlite3_open(FileName.c_str(), &Db));

  char *zErrMsg = 0;
  checkSqlite(sqlite3_exec(
      Db, "create TABLE IF NOT EXISTS words (word varchar);", nullptr,
      0, &zErrMsg));

  const char **Tail = 0;

  checkSqlite(sqlite3_prepare_v3(
      Db, "insert into words values (?);", -1, 0, &AddStatement,
      Tail /* OUT: Pointer to unused portion of zSql */
      ));

  checkSqlite(sqlite3_prepare_v3(
      Db, "select count(*) from words where word=?;", -1, 0,
      &CheckStatement,
      Tail /* OUT: Pointer to unused portion of zSql */
      ));
}

LookupEngine::~LookupEngine() {
  sqlite3_finalize(AddStatement);
  sqlite3_finalize(CheckStatement);
  sqlite3_close(Db);
}

void LookupEngine::AddEntry(const std::string &Word) {
  checkSqlite(sqlite3_bind_text(AddStatement, 1, Word.c_str(),
                                Word.length(), SQLITE_TRANSIENT));
  sqlite3_step(AddStatement);
  checkSqlite(sqlite3_reset(CheckStatement));
}

int LookupEngine::CheckEntry(const std::string &Word) {
  checkSqlite(sqlite3_bind_text(CheckStatement, 1, Word.c_str(),
                                Word.length(), SQLITE_TRANSIENT));
  sqlite3_step(CheckStatement);  // first result row
  int retVal = sqlite3_column_int(CheckStatement, 0);
  sqlite3_step(CheckStatement);  // at the end
  checkSqlite(sqlite3_reset(CheckStatement));

  return retVal;
}

}  // namespace OasisDigital
