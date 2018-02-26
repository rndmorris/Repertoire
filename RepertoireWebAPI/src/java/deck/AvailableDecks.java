/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deck;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rndmorris
 */
@Entity
@Table(name = "AvailableDecks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvailableDecks.findAll", query = "SELECT a FROM AvailableDecks a")
    , @NamedQuery(name = "AvailableDecks.findByDeckId", query = "SELECT a FROM AvailableDecks a WHERE a.deckId = :deckId")
    , @NamedQuery(name = "AvailableDecks.findByDeckName", query = "SELECT a FROM AvailableDecks a WHERE a.deckName = :deckName")
    , @NamedQuery(name = "AvailableDecks.findByCreatedBy", query = "SELECT a FROM AvailableDecks a WHERE a.createdBy = :createdBy")
    , @NamedQuery(name = "AvailableDecks.findByDescription", query = "SELECT a FROM AvailableDecks a WHERE a.description = :description")
    , @NamedQuery(name = "AvailableDecks.findByDefinitionUpdatedOn", query = "SELECT a FROM AvailableDecks a WHERE a.definitionUpdatedOn = :definitionUpdatedOn")
    , @NamedQuery(name = "AvailableDecks.findByFileUpdatedOn", query = "SELECT a FROM AvailableDecks a WHERE a.fileUpdatedOn = :fileUpdatedOn")
    , @NamedQuery(name = "AvailableDecks.findByVersionId", query = "SELECT a FROM AvailableDecks a WHERE a.versionId = :versionId")
    , @NamedQuery(name = "AvailableDecks.findByLatestVersion", query = "SELECT a FROM AvailableDecks a WHERE a.latestVersion = :latestVersion")})
public class AvailableDecks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DeckId")
    @Id
    private long deckId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "DeckName")
    private String deckName;
    @Size(max = 32)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Size(max = 2048)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DefinitionUpdatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date definitionUpdatedOn;
    @Column(name = "FileUpdatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fileUpdatedOn;
    @Column(name = "VersionId")
    private BigInteger versionId;
    @Column(name = "LatestVersion")
    private Integer latestVersion;

    public AvailableDecks() {
    }

    public long getDeckId() {
        return deckId;
    }

    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDefinitionUpdatedOn() {
        return definitionUpdatedOn;
    }

    public void setDefinitionUpdatedOn(Date definitionUpdatedOn) {
        this.definitionUpdatedOn = definitionUpdatedOn;
    }

    public Date getFileUpdatedOn() {
        return fileUpdatedOn;
    }

    public void setFileUpdatedOn(Date fileUpdatedOn) {
        this.fileUpdatedOn = fileUpdatedOn;
    }

    public BigInteger getVersionId() {
        return versionId;
    }

    public void setVersionId(BigInteger versionId) {
        this.versionId = versionId;
    }

    public Integer getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(Integer latestVersion) {
        this.latestVersion = latestVersion;
    }
    
}
