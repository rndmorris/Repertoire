\connect repertoire

CREATE FUNCTIOn rprtr_DeckDefinition_AddNew
(
	 p_DisplayName TEXT
	,p_OwnerRepUserId BIGINT
	,p_VisibilitySettingId INTEGER
	,p_ModifiedByRepUserId BIGINT
)
RETURNS void AS $$
DECLARE
	p_currtime TIMESTAMP WITH TIME ZONE := current_timestamp;
BEGIN
	INSERT INTO DeckDefinition
	(
		 CreatedOn
		,DisplayName
		,VisibilitySettingId
		,OwnerRepUserId
		,ModifiedOn
		,ModifiedByRepUserId
	)
	VALUES
	(
		 p_currtime
		,p_DisplayName
		,p_VisibilitySettingId
		,p_OwnerRepUserId
		,p_currtime
		,p_ModifiedByRepUserId
	);
END;
$$ LANGUAGE plpgsql;