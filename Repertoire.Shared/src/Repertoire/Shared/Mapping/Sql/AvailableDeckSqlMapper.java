/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Mapping.Sql;

import java.sql.ResultSet;
import Repertoire.Shared.Entities.AvailableDeck;
import Repertoire.Shared.EntityLists.AvailableDeckList;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rndmorris
 */
public class AvailableDeckSqlMapper implements ISqlMapper<AvailableDeckList> {
    
    @Override
    public AvailableDeckList MapEntityListFromResultSet(ResultSet rs) throws SQLException
    {
        AvailableDeckList list = new AvailableDeckList();
        List<AvailableDeck> availableDecks = new ArrayList<>();
        
        while (rs.next())
        {
            AvailableDeck row = new AvailableDeck();
            
            row.setCreatedBy(rs.getString("CreatedBy"));
            row.setCurrentVersion(rs.getInt("CurrentVersion"));
            row.setDeckId(rs.getInt("DeckId"));
            row.setDeckName(rs.getString("DeckName"));
            row.setDefinitionUpdatedOn(rs.getTimestamp("DefinitionUpdatedOn"));
            row.setDescription(rs.getString("Description"));
            row.setFileUpdatedOn(rs.getTimestamp("FileUpdatedOn"));
            row.setDeckId(rs.getLong("DeckId"));
            row.setVersionId(rs.getLong("VersionId"));
            
            availableDecks.add(row);
        }
        
        list.setContents(availableDecks);
        
        return list;
    }    
}
