use Repertoire;

SELECT '' AS 'Populating User';
INSERT INTO User VALUES
(1,'AdminTeam');

SELECT '' AS 'Populating VisibilitySetting';
INSERT INTO VisibilitySetting VALUES
	(0,'Public','Visible in the public library. Anyone can download it by Id.'),
	(1,'Unlisted','Not visible in the public library. Anyone can download it by Id.'),
	(2,'Private','Not visible in deck searches. No-one can download it, even by Id.');

CALL rprtr_DeckDefinition_AddNew('Test Deck 1',1,'PUBLIC',1);
CALL rprtr_DeckDefinition_AddNew('Test Deck 2',1,'PUBLIC',1);
CALL rprtr_DeckDefinition_AddNew('Test Deck 3',1,'PUBLIC',1);

CALL rprtr_DeckVersion_AddNew(1,1);
CALL rprtr_DeckVersion_AddNew(1,1);
CALL rprtr_DeckVersion_AddNew(2,1);
CALL rprtr_DeckVersion_AddNew(2,1);
CALL rprtr_DeckVersion_AddNew(2,1);
CALL rprtr_DeckVersion_AddNew(3,1);

UPDATE DeckDefinition SET Description = 'An example deck';