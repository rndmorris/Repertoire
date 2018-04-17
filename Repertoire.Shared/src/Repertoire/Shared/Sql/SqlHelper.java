/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Sql;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rndmorris
 */
public class SqlHelper {
    public static ResultSet CallStoredProcedure(Connection useConnection, String sprocName, List<SqlParameter> parameters)
            throws SQLException, UnsupportedOperationException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("call ").append(sprocName).append('(');
        for (SqlParameter param : parameters) {
            sb.append('?');
            sb.append(',');
        }
        sb.replace(sb.length()-1, sb.length(), ");");
        PreparedStatement statement = useConnection.prepareStatement(sb.toString());
        PopulatePreparedStatement(statement,parameters);
        
        statement.execute();
        
        return statement.getResultSet();
    }
    private static void PopulatePreparedStatement(PreparedStatement statement, List<SqlParameter> parameters) throws SQLException, UnsupportedOperationException
    {
        for (SqlParameter param : parameters)
        {
            int index = param.getIndex();
            Object val = param.getValue();
            switch(param.getType())
            {
                case ARRAY:
                    statement.setArray(index, (Array)val);
                    break;
                case BIGINT:
                    statement.setLong(index, (Long)val);
                    break;
                case BLOB:
                    statement.setBlob(index, (Blob)val);
                    break;
                case BOOLEAN:
                    statement.setBoolean(index, (Boolean)val);
                    break;
                case CLOB:
                    statement.setClob(index, (Clob)val);
                    break;
                case DATE:
                    statement.setDate(index, (Date)val);
                    break;
                case DECIMAL:
                    statement.setDouble(index, (Double)val);
                    break;
                case INTEGER:
                    statement.setInt(index, (Integer)val);
                    break;
                case NULL:
                    statement.setNull(index,param.getType().getValue());
                    break;
                case TIMESTAMP:
                    statement.setTimestamp(index, (Timestamp)val);
                    break;
                case VARCHAR:
                    statement.setString(index, (String)val);
                    break;
                default:
                    throw new UnsupportedOperationException(param.getType().name() + "NOT YET SUPPORTED");
            }
        }
    }
    public static ResultSet ExecuteQuery(Connection useConnection, String query, List<SqlParameter> parameters) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = useConnection.prepareStatement(query);
            PopulatePreparedStatement(statement,parameters);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement.getResultSet();
    }
}
