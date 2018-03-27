DELIMITER //
CREATE PROCEDURE rprtr_DictionaryVersion_AddNew
(
	 p_DictionaryDefinitionId BIGINT
	,p_ModifiedByUserId BIGINT
)
BEGIN
	DECLARE oldVersion INT;
	DECLARE newVersion INT;
	SET oldVersion = (SELECT MAX(Version) FROM DictionaryVersion WHERE DictionaryDefinitionId = p_DictionaryDefinitionId LIMIT 1);
	SET oldVersion = (CASE WHEN oldVersion IS NULL THEN  0 ELSE oldVersion END);

	SET newVersion = oldVersion + 1;

	INSERT INTO DictionaryVersion
	(
		 DictionaryDefinitionId
		,Version
		,ModifiedOn
		,ModifiedByUserId
	)
	VALUES
	(
		 p_DictionaryDefinitionId
		,newVersion
		,NOW()
		,p_ModifiedByUserId
	);
END
//
DELIMITER ;