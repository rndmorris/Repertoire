/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Sql;

import Repertoire.Shared.EntityLists.AvailableDeckList;
import Repertoire.Shared.Mapping.Sql.AvailableDeckSqlMapper;
import Repertoire.Shared.Mapping.Xml.AvailableDeckXmlMapper;
import java.sql.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author rndmorris
 */
public class RepertoireDB extends MysqlDataSource{
    
    @Override
    public Connection getConnection()throws SQLException
    {
        return super.getConnection(TempConstants.USERNAME,TempConstants.PASSWORD);
    }
    
    public RepertoireDB()
    {
        this.setURL("jdbc:mysql://localhost:3306/Repertoire");
        this.setDatabaseName("Repertoire");
    }
    
}
