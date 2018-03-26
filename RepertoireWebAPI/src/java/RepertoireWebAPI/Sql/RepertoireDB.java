/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepertoireWebAPI.Sql;

import Repertoire.Shared.EntityLists.AvailableDeckList;
import Repertoire.Shared.Mapping.Sql.AvailableDeckSqlMapper;
import Repertoire.Shared.Mapping.Xml.AvailableDeckXmlMapper;
import java.sql.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.JAXBException;
import org.w3c.dom.Document;

/**
 *
 * @author rndmorris
 */
public class RepertoireDB extends MysqlDataSource{
    
    public static void main(String[] args) throws SQLException, JAXBException
    {
        RepertoireDB db = new RepertoireDB();
        Connection conn = db.getConnection("rndmorris", "");
        PreparedStatement statement = conn.prepareStatement("select * from AvailableDeck;",new String[]{});
        statement.executeQuery();
        ResultSet results = statement.getResultSet();
        
        AvailableDeckList list = new AvailableDeckSqlMapper().MapEntityListFromResultSet(results);
        String xml = new AvailableDeckXmlMapper().stringFromEntityList(list);
        System.out.print(xml);
        boolean t = true;
    }
    
    public RepertoireDB()
    {
        this.setURL("jdbc:mysql://localhost:3306/Repertoire");
        this.setDatabaseName("Repertoire");
    }
    
}
