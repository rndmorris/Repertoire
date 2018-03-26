--	
--	
--	
CREATE FUNCTION rprtr_AvailableDeck_select
(
)
RETURNS TABLE (
	 DeckId BIGINT
	,DeckName TEXT
	,CreatedBy TEXT
	,Description TEXT
	,DefinitionUpdatedOn TIMESTAMP WITH TIME ZONE
	,FileUpdatedOn TIMESTAMP WITH TIME ZONE
	,VersionId BIGINT
	,CurrentDeckVersion INTEGER
)
AS $$
BEGIN
	RETURN QUERY
	SELECT	 def.Id AS DeckId
			,def.DisplayName AS DeckName
			,u.UserName AS CreatedBy
			,def.Description AS Description
			,def.ModifiedOn AS DefinitionUpdatedOn
			,ver.ModifiedOn AS FileUpdatedOn
			,ver.Id AS VersionId
			,ver.DeckVersion AS CurrentDeckVersion
	FROM DeckDefinition AS def
	LEFT JOIN RepUser u ON def.OwnerRepUserId = u.Id
	LEFT JOIN LatestDeckVersion ver ON def.Id = ver.DeckDefinitionId
	WHERE def.VisibilitySettingId = 0;
END;
$$ LANGUAGE plpgsql;