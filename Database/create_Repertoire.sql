SELECT '' AS 'Creating database';
CREATE DATABASE Repertoire;
USE Repertoire;

SELECT '' AS 'Creating tables';
CREATE TABLE User (
	Id BIGINT NOT NULL AUTO_INCREMENT,
	UserName VARCHAR(32) NOT NULL,
	PRIMARY KEY (Id)
);
CREATE TABLE VisibilitySetting (
	Id TINYINT NOT NULL UNIQUE,
	Name VARCHAR(64) NOT NULL UNIQUE,
	Description VARCHAR(256) DEFAULT NULL,
	PRIMARY KEY (Id)
);
CREATE TABLE DeckDefinition (
	Id BIGINT NOT NULL AUTO_INCREMENT,
	CreatedOn TIMESTAMP NOT NULL,
	DisplayName VARCHAR(255) NOT NULL,
	VisibilitySettingId TINYINT NOT NULL,
	OwnerUserId BIGINT NOT NULL,
	DeckVersionId BIGINT NOT NULL,
	ModifiedOn TIMESTAMP NOT NULL,
	ModifiedByUserId BIGINT NOT NULL,
	PRIMARY KEY (Id)
);
CREATE TABLE DeckVersion (
	Id BIGINT NOT NULL AUTO_INCREMENT,
	DeckDefinitionId BIGINT NOT NULL,
	DeckDataFile VARCHAR(255),
	Version INT NOT NULL,
	ModifiedOn TIMESTAMP NOT NULL,
	ModifiedByUserId BIGINT NOT NULL,
	PRIMARY KEY (Id)
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

SELECT '' AS 'Populating User';
INSERT INTO User VALUES
(1,'AdminTeam');

SELECT '' AS 'Populating VisibilitySetting';
INSERT INTO VisibilitySetting VALUES
	(0,'Public','Visible in the public library. Anyone can download it by Id.'),
	(1,'Unlisted','Not visible in the public library. Anyone can download it by Id.'),
	(2,'Private','Not visible in deck searches. No-one can download it, even by Id.');