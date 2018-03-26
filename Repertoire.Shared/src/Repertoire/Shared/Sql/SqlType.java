/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Sql;

import java.sql.Types;

/**
 *
 * @author rndmorris
 */
public enum SqlType {
     ARRAY(Types.ARRAY)
    ,BIGINT(Types.BIGINT)
    ,BINARY(Types.BINARY)
    ,BIT(Types.BIT)
    ,BLOB(Types.BLOB)
    ,BOOLEAN(Types.BOOLEAN)
    ,CHAR(Types.CHAR)
    ,CLOB(Types.CLOB)
    ,DATALINK(Types.DATALINK)
    ,DATE(Types.DATE)
    ,DECIMAL(Types.DECIMAL)
    ,DISTINCT(Types.DISTINCT)
    ,DOUBLE(Types.DOUBLE)
    ,FLOAT(Types.FLOAT)
    ,INTEGER(Types.INTEGER)
    ,JAVA_OBJECT(Types.JAVA_OBJECT)
    ,LONGNVARCHAR(Types.LONGNVARCHAR)
    ,LONGVARBINARY(Types.LONGVARBINARY)
    ,LONGVARCHAR(Types.LONGVARCHAR)
    ,NCHAR(Types.NCHAR)
    ,NCLOB(Types.NCLOB)
    ,NULL(Types.NULL)
    ,NUMERIC(Types.NUMERIC)
    ,NVARCHAR(Types.NVARCHAR)
    ,OTHER(Types.OTHER)
    ,REAL(Types.REAL)
    ,REF(Types.REF)
    ,REF_CURSOR(Types.REF_CURSOR)
    ,ROWID(Types.ROWID)
    ,SMALLINT(Types.SMALLINT)
    ,SQLXML(Types.SQLXML)
    ,STRUCT(Types.STRUCT)
    ,TIME(Types.TIME)
    ,TIME_WITH_TIMEZONE(Types.TIME_WITH_TIMEZONE)
    ,TIMESTAMP(Types.TIMESTAMP)
    ,TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE)
    ,TINYINT(Types.TINYINT)
    ,VARBINARY(Types.VARBINARY)
    ,VARCHAR(Types.VARCHAR);
    
    private final int id;
    SqlType(int id) { this.id = id; }
    public int getValue() { return id; }
}
