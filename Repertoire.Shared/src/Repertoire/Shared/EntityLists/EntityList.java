/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.EntityLists;

import Repertoire.Shared.Entities.Entity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author rndmorris
 * @param <EntityType>
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class EntityList<EntityType extends Entity> extends ArrayList<EntityType>{
    protected abstract List<EntityType> getContents();
    public void replaceAndAppendRange(int startIndex, Iterable<EntityType> replaceFrom)
    {
        if (0 > startIndex || startIndex > size())
        {
            throw new IllegalArgumentException();
        }
        Iterator<EntityType> iter = replaceFrom.iterator();
        EntityType nextEntity;
        while ((nextEntity = iter.next()) != null)
        {
            if (size() <= startIndex)
            {
                add(nextEntity);
            }
            else
            {
                this.set(startIndex, nextEntity);
            }
            
            startIndex++;
        }
    }
}
