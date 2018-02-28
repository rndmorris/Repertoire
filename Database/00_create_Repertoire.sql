
CREATE DATABASE Repertoire;
\connect repertoire;

CREATE TABLE RepUser (
	 Id BIGSERIAL NOT NULL
	,UserName TEXT NOT NULL UNIQUE
	,PRIMARY KEY (Id)
);
CREATE TABLE VisibilitySetting (
	 Id SERIAL NOT NULL
	,Name Text NOT NULL UNIQUE
	,Description TEXT DEFAULT ''
	,PRIMARY KEY (Id)
);
CREATE TABLE DeckDefinition (
	 Id BIGSERIAL NOT NULL
	,CreatedOn TIMESTAMP WITH TIME ZONE NOT NULL
	,DisplayName TEXT NOT NULL
	,Description TEXT DEFAULT ''
	,VisibilitySettingId INTEGER NOT NULL
	,OwnerRepUserId BIGINT NOT NULL
	,ModifiedOn TIMESTAMP WITH TIME ZONE NOT NULL
	,ModifiedByRepUserId BIGINT NOT NULL
	,PRIMARY KEY (Id)
);
CREATE TABLE DeckVersion (
	 Id BIGSERIAL NOT NULL
	,DeckDefinitionId BIGINT NOT NULL
	,Version INT NOT NULL
	,ModifiedOn TIMESTAMP WITH TIME ZONE NOT NULL
	,ModifiedByRepUserId BIGINT NOT NULL
	,PRIMARY KEY (Id)
);

ALTER TABLE DeckDefinition 
	ADD FOREIGN KEY (OwnerRepUserId) REFERENCES RepUser(Id);
ALTER TABLE DeckDefinition
	ADD FOREIGN KEY (ModifiedByRepUserId) REFERENCES RepUser(Id);
ALTER TABLE DeckDefinition
	ADD FOREIGN KEY (VisibilitySettingId) REFERENCES VisibilitySetting(Id);

CREATE VIEW LatestDeckVersion AS 
	SELECT 	 DeckDefinitionId
			,Id
			,Version AS DeckVersion
			,ModifiedOn
	FROM DeckVersion dv1
	WHERE dv1.Version = (
		SELECT MAX(Version)
		FROM DeckVersion dv2
		WHERE dv2.DeckDefinitionId = dv1.DeckDefinitionId
	);


CREATE VIEW AvailableDeck AS
	SELECT	 ROW_NUMBER() OVER (ORDER BY def.Id) AS EntryNum
			,def.Id AS DeckId
			,def.DisplayName AS DeckName
			,u.UserName AS CreatedBy
			,def.Description AS Description
			,def.ModifiedOn AS DefinitionUpdatedOn
			,ver.ModifiedOn AS FileUpdatedOn
			,ver.Id AS VersionId
			,ver.DeckVersion AS CurrentDeckVersion
	FROM DeckDefinition AS def
	LEFT JOIN RepUser u ON def.OwnerRepUserId = u.Id
	LEFT JOIN LatestDeckVersion ver ON def.Id = ver.DeckDefinitionId
	WHERE def.VisibilitySettingId = 0;