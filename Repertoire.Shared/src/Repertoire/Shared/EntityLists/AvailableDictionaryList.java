/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.EntityLists;

import Repertoire.Shared.Entities.AvailableDictionary;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rndmorris
 */
@XmlRootElement(name="availableDictionaryList")
@XmlAccessorType(XmlAccessType.NONE)
public class AvailableDictionaryList extends EntityList<AvailableDictionary>{
    /**
     *
     * @return
     */
    @XmlElement(name="availableDictionary")
    @Override
    protected List<AvailableDictionary> getContents()
    {
        return (List<AvailableDictionary>)this;
    }
}
