package com.ambition.sql.parser.ddl;

import static java.util.Objects.requireNonNull;

import java.util.List;
import org.apache.calcite.sql.SqlCall;
import org.apache.calcite.sql.SqlIdentifier;
import org.apache.calcite.sql.SqlKind;
import org.apache.calcite.sql.SqlLiteral;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlOperator;
import org.apache.calcite.sql.SqlSpecialOperator;
import org.apache.calcite.sql.SqlWriter;
import org.apache.calcite.sql.parser.SqlParserPos;
import org.apache.calcite.util.ImmutableNullableList;
import org.apache.calcite.util.NlsString;

/**
 * Properties of a DDL, consist of key value pairs.
 */
public class SqlProperty extends SqlCall {

  /** Use this operator only if you don't have a better one. */
  protected static final SqlOperator OPERATOR =
      new SqlSpecialOperator("Property", SqlKind.OTHER);

  private final SqlIdentifier key;
  private final SqlNode value;

  public SqlProperty(SqlIdentifier key, SqlNode value, SqlParserPos pos) {
    super(pos);
    this.key = requireNonNull(key, "Property key is missing");
    this.value = requireNonNull(value, "Property value is missing");
  }

  public SqlIdentifier getKey() {
    return key;
  }

  public SqlNode getValue() {
    return value;
  }

  public String getKeyString() {
    return key.toString();
  }

  public String getValueString() {
    return ((NlsString) SqlLiteral.value(value)).getValue();
  }

  @Override
  public SqlOperator getOperator() {
    return OPERATOR;
  }

  @Override
  public List<SqlNode> getOperandList() {
    return ImmutableNullableList.of(key, value);
  }

  @Override
  public void unparse(
      SqlWriter writer,
      int leftPrec,
      int rightPrec) {
    key.unparse(writer, leftPrec, rightPrec);
    writer.keyword("=");
    value.unparse(writer, leftPrec, rightPrec);
  }
}

// End SqlProperty.java
