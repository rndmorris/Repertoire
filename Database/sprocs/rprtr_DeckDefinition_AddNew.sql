DELIMITER //
CREATE PROCEDURE rprtr_DeckDefinition_AddNew
(
	 p_DisplayName VARCHAR(256)
	,p_OwnerUserId BIGINT
	,p_VisibilitySetting VARCHAR(64)
	,p_ModifiedByUserId BIGINT
)
BEGIN
	INSERT INTO DeckDefinition
	(
		 CreatedOn
		,DisplayName
		,VisibilitySettingId
		,OwnerUserId
		,ModifiedOn
		,ModifiedByUserId
	)
	VALUES
	(
		 NOW()
		,p_DisplayName
		,(SELECT Id FROM VisibilitySetting WHERE UPPER(Name) = UPPER(p_VisibilitySetting) LIMIT 1)
		,p_OwnerUserId
		,NOW()
		,p_ModifiedByUserId
	);
END
//
DELIMITER ;