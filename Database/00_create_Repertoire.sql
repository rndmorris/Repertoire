SELECT '' AS 'Creating database';
CREATE DATABASE Repertoire;
USE Repertoire;

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
CREATE TABLE DeckDefinition (
	 Id BIGINT NOT NULL AUTO_INCREMENT
	,CreatedOn TIMESTAMP NOT NULL
	,DisplayName VARCHAR(256) NOT NULL
	,Description VARCHAR(2048)
	,VisibilitySettingId TINYINT NOT NULL
	,OwnerUserId BIGINT NOT NULL
	,DeckVersionId BIGINT DEFAULT NULL
	,ModifiedOn TIMESTAMP NOT NULL
	,ModifiedByUserId BIGINT NOT NULL
	,PRIMARY KEY (Id)
);
CREATE TABLE DeckVersion (
	 Id BIGINT NOT NULL AUTO_INCREMENT
	,DeckDefinitionId BIGINT NOT NULL
	,FilePath VARCHAR(255)
	,Version INT NOT NULL
	,ModifiedOn TIMESTAMP NOT NULL
	,ModifiedByUserId BIGINT NOT NULL
	,PRIMARY KEY (Id)
);

SELECT '' AS 'Creating foreign keys';
ALTER TABLE DeckDefinition 
	ADD CONSTRAINT fk_OwnerUserId FOREIGN KEY (OwnerUserId) REFERENCES User(Id);
ALTER TABLE DeckDefinition
	ADD CONSTRAINT fk_ModifiedByUserId FOREIGN KEY (ModifiedByUserId) REFERENCES User(Id);
ALTER TABLE DeckDefinition
	ADD CONSTRAINT fk_VisibilitySettingId FOREIGN KEY (VisibilitySettingId) REFERENCES VisibilitySetting(Id);
ALTER TABLE DeckDefinition 
	ADD CONSTRAINT fk_DeckVersionId FOREIGN KEY (DeckVersionId) REFERENCES DeckVersion(Id);
ALTER TABLE DeckVersion
	ADD CONSTRAINT fk_DeckDefinitionId FOREIGN KEY (DeckDefinitionId) REFERENCES DeckDefinition(Id);