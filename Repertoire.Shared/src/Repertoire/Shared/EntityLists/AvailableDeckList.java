/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.EntityLists;

import Repertoire.Shared.Entities.AvailableDeck;
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
@XmlRootElement(name="AvailableDecks")
@XmlAccessorType(XmlAccessType.NONE)
public class AvailableDeckList extends EntityList<AvailableDeck>{
    /**
     *
     * @return
     */
    @XmlElement(name="AvailableDeck")
    private List<AvailableDeck> getAvailableDeckList()
    {
        return this.getContents();
    }
    @Override
    public Class getEntityListClass()
    {
        return this.getClass();
    }
}
