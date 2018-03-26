/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.EntityLists;

import Repertoire.Shared.Entities.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author rndmorris
 * @param <EntityType>
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class EntityList<EntityType extends Entity>{
    private List<EntityType> contents = null;
    public EntityList()
    {
        this(new ArrayList<EntityType>());
    }
    public EntityList(List<EntityType> contents)
    {
        setContents(contents);
    }
    public List<EntityType> getContents()
    {
        return contents;
    }
    public void setContents(List<EntityType> value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("parameter \"value\" cannot be null.");
        }
        contents = value;
    }
    
    public abstract Class getEntityListClass();
}
