/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Sql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 *
 * @author rndmorris
 */
public class RepertoireDB extends MysqlDataSource{
    
    public RepertoireDB(String url)
    {
        this.setURL(url);
    }
}
