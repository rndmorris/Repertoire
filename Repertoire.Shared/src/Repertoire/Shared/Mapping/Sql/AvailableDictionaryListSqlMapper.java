/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Mapping.Sql;

import java.sql.ResultSet;
import Repertoire.Shared.Entities.AvailableDictionary;
import Repertoire.Shared.EntityLists.AvailableDictionaryList;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rndmorris
 */
public class AvailableDictionaryListSqlMapper implements ISqlMapper<AvailableDictionaryList> {
    
    @Override
    public AvailableDictionaryList MapEntityListFromResultSet(ResultSet rs) throws SQLException
    {
        AvailableDictionaryList list = new AvailableDictionaryList();
        
        while (rs.next())
        {
            AvailableDictionary row = new AvailableDictionary();
            
            row.setCreatedBy(rs.getString("CreatedBy"));
            row.setCurrentVersion(rs.getInt("CurrentVersion"));
            row.setDictionaryId(rs.getInt("DictionaryId"));
            row.setDictionaryName(rs.getString("DictionaryName"));
            row.setDefinitionUpdatedOn(rs.getTimestamp("DefinitionUpdatedOn"));
            row.setDescription(rs.getString("Description"));
            row.setFileUpdatedOn(rs.getTimestamp("FileUpdatedOn"));
            row.setDictionaryId(rs.getLong("DictionaryId"));
            row.setVersionId(rs.getLong("VersionId"));
            row.setFilePath(rs.getString("FilePath"));
            list.add(row);
        }
        
        return list;
    }    
}
