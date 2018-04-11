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
CALL rprtr_DictionaryDefinition_AddNew('Test Dictionary 4','This is a test dictionary.','PUBLIC',1);
CALL rprtr_DictionaryDefinition_AddNew('Test Dictionary 5','This is a test dictionary.','PUBLIC',1);
CALL rprtr_DictionaryDefinition_AddNew('Test Dictionary 6','This is a test dictionary.','PUBLIC',1);
CALL rprtr_DictionaryDefinition_AddNew('Test Dictionary 7','This is a test dictionary.','PUBLIC',1);
CALL rprtr_DictionaryDefinition_AddNew('Test Dictionary 8','This is a test dictionary.','PUBLIC',1);
CALL rprtr_DictionaryDefinition_AddNew('Test Dictionary 9','This is a test dictionary.','PUBLIC',1);
CALL rprtr_DictionaryDefinition_AddNew('Test Dictionary 10','This is a test dictionary.','PUBLIC',1);
CALL rprtr_DictionaryDefinition_AddNew('Test Dictionary 11','This is a test dictionary.','PUBLIC',1);

CALL rprtr_DictionaryVersion_AddNew(1,1);
CALL rprtr_DictionaryVersion_AddNew(1,1);
CALL rprtr_DictionaryVersion_AddNew(2,1);
CALL rprtr_DictionaryVersion_AddNew(2,1);
CALL rprtr_DictionaryVersion_AddNew(2,1);
CALL rprtr_DictionaryVersion_AddNew(3,1);
CALL rprtr_DictionaryVersion_AddNew(4,1);
CALL rprtr_DictionaryVersion_AddNew(5,1);
CALL rprtr_DictionaryVersion_AddNew(6,1);
CALL rprtr_DictionaryVersion_AddNew(7,1);
CALL rprtr_DictionaryVersion_AddNew(8,1);
CALL rprtr_DictionaryVersion_AddNew(9,1);
CALL rprtr_DictionaryVersion_AddNew(10,1);
CALL rprtr_DictionaryVersion_AddNew(11,1);