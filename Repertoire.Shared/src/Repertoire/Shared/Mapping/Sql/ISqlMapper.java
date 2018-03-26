/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Mapping.Sql;

import Repertoire.Shared.EntityLists.EntityList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rndmorris
 * @param <EntityListType>
 */
public interface ISqlMapper<EntityListType extends EntityList> {
    public EntityListType MapEntityListFromResultSet(ResultSet rs) throws SQLException;
}
