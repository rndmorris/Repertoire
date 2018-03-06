\connect repertoire;

CREATE FUNCTION rprtr_DeckVersion_AddNew
(
	 p_DeckDefinitionId BIGINT
	,p_ModifiedByRepUserId BIGINT
)
RETURNS void AS $$
DECLARE
	p_currtime TIMESTAMP WITH TIME ZONE := current_timestamp;
	oldVersion INTEGER;
	newVersion INTEGER;
BEGIN
	oldVersion := (SELECT MAX(Version) FROM DeckVersion WHERE DeckDefinitionId = p_DeckDefinitionId LIMIT 1);
	newVersion := (CASE WHEN oldVersion IS NULL THEN  1 ELSE (oldVersion + 1) END);
	INSERT INTO DeckVersion
	(
		 DeckDefinitionId
		,Version
		,ModifiedOn
		,ModifiedByRepUserId
	)
	VALUES
	(
		 p_DeckDefinitionId
		,newVersion
		,p_currtime
		,p_ModifiedByRepUserId
	);
END;
$$ LANGUAGE plpgsql;