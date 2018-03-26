/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Mapping.Xml.Adapters;

import java.sql.Timestamp;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author rndmorris
 */
public class TimestampToDateAdapter extends XmlAdapter <java.util.Date, Timestamp> {

    @Override
    public Timestamp unmarshal(Date vt) throws Exception {
        return new Timestamp(vt.getTime());
    }

    @Override
    public Date marshal(Timestamp bt) throws Exception {
        return new Date(bt.getTime());
    }
    
}
