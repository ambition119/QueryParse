package com.ambition.sql.parser.ddl;


import static org.apache.calcite.sql.parser.SqlParser.DEFAULT_IDENTIFIER_MAX_LENGTH;

import com.ambition.sql.parser.impl.AmSqlParserImpl;
import java.io.StringReader;
import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlNodeList;
import org.junit.Test;

public class SqlCreateTableTest {
  @Test
  public void testCreateTable() throws Exception {
    String s1 = null;

    String ddl = "CREATE TABLE tbl1 (\n" +
        "  a bigint,\n" +
        "  h varchar, \n" +
        "  g bigint, \n" +
        "  ts timestamp, \n" +
        "  b varchar\n" +
        ")\n" +
        "WITH (\n" +
        "    connector = 'kafka', \n" +
        "    kafka.topic = 'log.test'\n" +
        ")\n";
    StringReader in = new StringReader(ddl);
    AmSqlParserImpl impl = new AmSqlParserImpl(in);
    impl.switchTo("BTID");
    impl.setTabSize(1);
    impl.setQuotedCasing(Lex.JAVA.quotedCasing);
    impl.setUnquotedCasing(Lex.JAVA.unquotedCasing);
    impl.setIdentifierMaxLength(DEFAULT_IDENTIFIER_MAX_LENGTH);

    SqlNode node = impl.parseSqlStmtEof();
    if (node instanceof SqlCreateTable) {
      SqlCreateTable createTable = (SqlCreateTable) node;
      SqlNodeList columnList = createTable.getColumnList();
      SqlNodeList propertyList = createTable.getPropertyList();
    }

  }

}
