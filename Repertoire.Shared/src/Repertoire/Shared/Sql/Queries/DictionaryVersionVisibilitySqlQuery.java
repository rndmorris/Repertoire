/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Sql.Queries;

import Repertoire.Shared.Mapping.Sql.AvailableDictionaryListSqlMapper;
import Repertoire.Shared.Sql.SqlHelper;
import Repertoire.Shared.Sql.SqlParameter;
import Repertoire.Shared.Sql.SqlType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rndmorris
 */
public class DictionaryVersionVisibilitySqlQuery {

    /**
     *
     * @param useConnection
     * @param versionId
     * @return -1 if not found, else 0 inclusive through 2 inclusive.
     */
    public static int execute(Connection useConnection, int versionId) {
        int output = -1;
        
        StringBuilder query = new StringBuilder();
        
        query.append("SELECT VisibilitySettingId")
                .append(" FROM DictionaryDefinition")
                .append(" JOIN DictionaryVersion ON")
                .append(" DictionaryDefinition.Id = DictionaryVersion.DictionaryDefinitionId")
                .append(" WHERE  DictionaryVersion.Id = ? LIMIT 1;");
        List<SqlParameter> params = getParameters(versionId);
        
        try {
            ResultSet rsData = SqlHelper.ExecuteQuery(useConnection, query.toString(), params);
            while (rsData.next())
            {
                output = rsData.getInt("VisibilitySettingId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvailableDictionarySQLQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    private static List<SqlParameter> getParameters(int versionId) {
        List<SqlParameter> output = new ArrayList<>(1);
        
        output.add(new SqlParameter(SqlType.INTEGER,versionId,1));
        
        return output;
    }
}
