DELIMITER //
CREATE PROCEDURE rprtr_DictionaryDefinition_AddNew
(
	 p_DisplayName VARCHAR(256)
	,p_Description VARCHAR(2048)
	,p_VisibilitySettingName VARCHAR(64)
	,p_OwnerUserId BIGINT
)
BEGIN
	DECLARE v_VisibilitySettingId TINYINT;
	SET v_VisibilitySettingId = (SELECT Id FROM VisibilitySetting WHERE UPPER(TRIM(Name)) = UPPER(TRIM(p_VisibilitySettingName)) LIMIT 1);

	INSERT INTO DictionaryDefinition
	(
		 CreatedOn
		,DisplayName
		,Description
		,VisibilitySettingId
		,OwnerUserId
		,ModifiedOn
		,ModifiedByUserId
	)
	VALUES
	(
		 NOW()
		,p_DisplayName
		,p_Description
		,v_VisibilitySettingId
		,p_OwnerUserId
		,NOW()
		,p_OwnerUserId
	);
END
//
DELIMITER ;