package com.ambition.sql.parser.ddl;

/**
 * @Author: wpl
 * @Date: 2019/6/18
 */
public enum SqlColumnType {
  BOOLEAN,
  TINYINT,
  SMALLINT,
  INT,
  INTEGER,
  BIGINT,
  REAL,
  FLOAT,
  DOUBLE,
  DECIMAL,
  DATE,
  TIME,
  TIMESTAMP,
  VARCHAR,
  VARBINARY,
  ANY,
  ARRAY,
  MAP,
  ROW,
  UNSUPPORTED;

  /** Returns the column type with the string representation. **/
  public static SqlColumnType getType(String type) {
    if (type == null) {
      return UNSUPPORTED;
    }
    try {
      return SqlColumnType.valueOf(type.toUpperCase());
    } catch (IllegalArgumentException var1) {
      return UNSUPPORTED;
    }
  }

  /** Returns true if this type is unsupported. **/
  public boolean isUnsupported() {
    return this.equals(UNSUPPORTED);
  }
}

