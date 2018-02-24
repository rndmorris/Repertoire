use Repertoire;

SELECT '' AS 'Populating User';
INSERT INTO User VALUES
(1,'AdminTeam');

SELECT '' AS 'Populating VisibilitySetting';
INSERT INTO VisibilitySetting VALUES
	(0,'Public','Visible in the public library. Anyone can download it by Id.'),
	(1,'Unlisted','Not visible in the public library. Anyone can download it by Id.'),
	(2,'Private','Not visible in deck searches. No-one can download it, even by Id.');

--SELECT '' AS 'Populating DeckDefinition with sample data';
--INERT INTO DeckDefinition VALUES
--	()

--SELECT '' AS 'Populating DeckVersion with sample data';
--INSERT INTO DeckVersion (CreatedOn,DisplayName,VisibilitySettingId,OwnerUserId,DeckVersionId,ModifiedOn,ModifiedByUserId) VALUES
--	()