/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Entities;

import Repertoire.Shared.Mapping.Xml.Adapters.TimestampToDateAdapter;
import java.sql.Timestamp;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author rndmorris
 */
@XmlRootElement(name="AvailableDictionary")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AvailableDictionary extends Entity {

    private Long DictionaryId;
    private String DictionaryName;
    private String CreatedBy;
    private String Description;
    private Timestamp DefinitionUpdatedOn;
    private Timestamp FileUpdatedOn;
    private long VersionId;
    private int CurrentVersion;
    
    public long getDictionaryId()
    {
        return DictionaryId;
    }
    public void setDictionaryId(long value)
    {
        DictionaryId = value;
    }
    
    public String getDictionaryName()
    {
        return DictionaryName;
    }
    public void setDictionaryName(String value)
    {
        DictionaryName = value;
    }
    
    public String getCreatedBy()
    {
        return CreatedBy;
    }
    public void setCreatedBy(String value)
    {
        CreatedBy = value;
    }
    
    public String getDescription()
    {
        return Description;
    }
    public void setDescription(String value)
    {
        Description = value;
    }
    
    @XmlJavaTypeAdapter(type=Date.class, value=TimestampToDateAdapter.class)
    public Timestamp getDefinitionUpdatedOn()
    {
        return DefinitionUpdatedOn;
    }
    public void setDefinitionUpdatedOn(Timestamp value)
    {
        DefinitionUpdatedOn = value;
    }
    @XmlJavaTypeAdapter(type=Date.class, value=TimestampToDateAdapter.class)
    public Timestamp getFileUpdatedOn()
    {
        return FileUpdatedOn;
    }
    public void setFileUpdatedOn(Timestamp value)
    {
        FileUpdatedOn = value;
    }
    
    public long getVersionId()
    {
        return VersionId;
    }
    public void setVersionId(long value)
    {
        VersionId = value;
    }
    
    public int getCurrentVersion()
    {
        return CurrentVersion;
    }
    public void setCurrentVersion(int value)
    {
        CurrentVersion = value;
    }
    
}
