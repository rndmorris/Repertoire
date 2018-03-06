/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.WebApi.Objects;

import java.util.Date;

/**
 *
 * @author rndmorris
 */
public class AvailableDeck {
    
    private long DeckId;
    public long getDeckId()
    {
        return DeckId;
    }
    public void setDeckId(long value)
    {
        DeckId = value;
    }
    
    private String DeckName;
    public String getDeckName()
    {
        return DeckName;
    }
    public void setDeckName(String value)
    {
        DeckName = value;
    }
    
    private String CreatedBy;
    public String getCreatedBy()
    {
        return CreatedBy;
    }
    public void setCreatedBy(String value)
    {
        CreatedBy = value;
    }
    
    private String Description;
    public String getDescription()
    {
        return Description;
    }
    public void setDescription(String value)
    {
        Description = value;
    }
    
    private Date DefinitionUpdatedOn;
    public Date getDefinitionUpdatedOn()
    {
        return DefinitionUpdatedOn;
    }
    public void setDefinitionUpdatedOn(Date value)
    {
        DefinitionUpdatedOn = value;
    }
    
    private Date FileUpdatedOn;
    public Date getFileUpdatedOn()
    {
        return FileUpdatedOn;
    }
    public void setFileUpdatedOn(Date value)
    {
        FileUpdatedOn = value;
    }
    
    private long VersionId;
    public long getVersionId()
    {
        return VersionId;
    }
    public void setVersionId(long value)
    {
        VersionId = value;
    }
    
    private int CurrentVersion;
    public int getCurrentVersion()
    {
        return CurrentVersion;
    }
    public void setCurrentVersion(int value)
    {
        CurrentVersion = value;
    }
    
}
