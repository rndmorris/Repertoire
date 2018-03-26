DELIMITER //
CREATE PROCEDURE rprtr_DeckVersion_AddNew
(
	 p_DeckDefinitionId BIGINT
	,p_ModifiedByUserId BIGINT
)
BEGIN
	DECLARE oldVersion INT;
	DECLARE newVersion INT;
	SET oldVersion = (SELECT MAX(Version) FROM DeckVersion WHERE DeckDefinitionId = p_DeckDefinitionId LIMIT 1);
	SET oldVersion = (CASE WHEN oldVersion IS NULL THEN  0 ELSE oldVersion END);

	SET newVersion = oldVersion + 1;

	INSERT INTO DeckVersion
	(
		 DeckDefinitionId
		,Version
		,ModifiedOn
		,ModifiedByUserId
	)
	VALUES
	(
		 p_DeckDefinitionId
		,newVersion
		,NOW()
		,p_ModifiedByUserId
	);
END
//
DELIMITER ;