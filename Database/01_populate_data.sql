SELECT '' AS 'Populating Internal User';
INSERT INTO User VALUES
(1,'AdminTeam');

SELECT '' AS 'Populating VisibilitySetting';
INSERT INTO VisibilitySetting VALUES
	(0,'Public','Visible in the public library. Anyone can download it by Id.'),
	(1,'Unlisted','Not visible in the public library. Anyone can download it by Id.'),
	(2,'Private','Not visible in dictionary searches. No-one can download it, even by Id.');

CALL rprtr_DictionaryDefinition_AddNew('Test Dictionary 1','This is a test dictionary.','public',1);
CALL rprtr_DictionaryDefinition_AddNew('Test Dictionary 2','This is a test dictionary.',' puBliC ',1);
CALL rprtr_DictionaryDefinition_AddNew('Test Dictionary 3','This is a test dictionary.','PUBLIC',1);

CALL rprtr_DictionaryVersion_AddNew(1,1);
CALL rprtr_DictionaryVersion_AddNew(1,1);
CALL rprtr_DictionaryVersion_AddNew(2,1);
CALL rprtr_DictionaryVersion_AddNew(2,1);
CALL rprtr_DictionaryVersion_AddNew(2,1);
CALL rprtr_DictionaryVersion_AddNew(3,1);
