/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Sql.Queries;

import Repertoire.Shared.EntityLists.AvailableDictionaryList;
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
public class AvailableDictionarySQLQuery {
    public static AvailableDictionaryList execute(Connection useConnection, int pageOffset, int pageSize, String[] searchTerms) {
        AvailableDictionaryList output = null;
        StringBuilder query = new StringBuilder();
        StringBuilder regexp = new StringBuilder();
        regexp.append("(");
        for (String term : searchTerms) {
            regexp.append(term).append('|');
        }
        regexp.replace(regexp.length()-1,regexp.length(),")");
        query.append("SELECT DictionaryId")
                .append(",DictionaryName")
                .append(",CreatedBy")
                .append(",Description")
                .append(",DefinitionUpdatedOn")
                .append(",FileUpdatedOn")
                .append(",VersionId")
                .append(",FilePath")
                .append(",CurrentVersion")
                .append(" FROM AvailableDictionary")
                .append(" WHERE DictionaryName REGEXP ").append(regexp.toString())
                .append(" OR    CreatedBy REGEXP ").append(regexp.toString())
                .append(" LIMIT ?, ?;");
        List<SqlParameter> params = getParameters(pageOffset, pageSize);
        try {
            ResultSet rsData = SqlHelper.ExecuteQuery(useConnection, query.toString(), params);
            output = new AvailableDictionaryListSqlMapper().MapEntityListFromResultSet(rsData);
        } catch (SQLException ex) {
            Logger.getLogger(AvailableDictionarySQLQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    private static List<SqlParameter> getParameters(int pageOffset, int pageSize) {
        List<SqlParameter> output = new ArrayList<>(2);
        
        output.add(new SqlParameter(SqlType.INTEGER,pageOffset,1));
        output.add(new SqlParameter(SqlType.INTEGER,pageSize,2));
        
        return output;
    }
}
