SELECT '' AS 'Creating tables';
CREATE TABLE User (
	 Id BIGINT NOT NULL AUTO_INCREMENT
	,UserName VARCHAR(32) NOT NULL UNIQUE
	,PRIMARY KEY (Id)
);
CREATE TABLE VisibilitySetting (
	 Id TINYINT NOT NULL UNIQUE
	,Name VARCHAR(64) NOT NULL UNIQUE
	,Description VARCHAR(256) DEFAULT NULL
	,PRIMARY KEY (Id)
);
CREATE TABLE DictionaryDefinition (
	 Id BIGINT NOT NULL AUTO_INCREMENT
	,CreatedOn TIMESTAMP NOT NULL
	,DisplayName VARCHAR(256) NOT NULL
	,Description VARCHAR(2048) DEFAULT ''
	,VisibilitySettingId TINYINT NOT NULL
	,OwnerUserId BIGINT NOT NULL
	,ModifiedOn TIMESTAMP NOT NULL
	,ModifiedByUserId BIGINT NOT NULL
	,PRIMARY KEY (Id)
);
CREATE TABLE DictionaryVersion (
	 Id BIGINT NOT NULL AUTO_INCREMENT
	,DictionaryDefinitionId BIGINT NOT NULL
	,FilePath VARCHAR(255)
	,Version INT NOT NULL
	,ModifiedOn TIMESTAMP NOT NULL
	,ModifiedByUserId BIGINT NOT NULL
	,PRIMARY KEY (Id)
);

SELECT '' AS 'Creating foreign keys';
ALTER TABLE DictionaryDefinition 
	ADD CONSTRAINT fk_OwnerUserId FOREIGN KEY (OwnerUserId) REFERENCES User(Id);
ALTER TABLE DictionaryDefinition
	ADD CONSTRAINT fk_ModifiedByUserId FOREIGN KEY (ModifiedByUserId) REFERENCES User(Id);
ALTER TABLE DictionaryDefinition
	ADD CONSTRAINT fk_VisibilitySettingId FOREIGN KEY (VisibilitySettingId) REFERENCES VisibilitySetting(Id);

SELECT '' AS 'Creating view LatestDictionaryVersion';
CREATE VIEW Repertoire.LatestDictionaryVersion AS
	SELECT	 DictionaryDefinitionId
			,Id
			,Version
			,ModifiedOn
	FROM DictionaryVersion ver1
	WHERE ver1.Version = (SELECT MAX(Version) FROM DictionaryVersion ver2 WHERE ver1.DictionaryDefinitionId = ver2.DictionaryDefinitionId);

SELECT '' AS 'Creating view AvailableDictionary';
CREATE VIEW Repertoire.AvailableDictionary AS
	SELECT	 def.Id AS 'DictionaryId'
			,def.DisplayName AS 'DictionaryName'
			,u.UserName AS 'CreatedBy'
			,def.Description AS 'Description'
			,def.ModifiedOn AS 'DefinitionUpdatedOn'
			,ver.ModifiedOn AS 'FileUpdatedOn'
			,ver.Id AS 'VersionId'
			,ver.Version AS 'CurrentVersion'
	FROM LatestDictionaryVersion AS ver
	LEFT JOIN (DictionaryDefinition AS def) ON (def.Id = ver.DictionaryDefinitionId)
	LEFT JOIN (User AS u) ON (def.OwnerUserId = u.Id)
	WHERE def.VisibilitySettingId = 0;