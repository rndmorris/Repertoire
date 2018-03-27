--	
--	
--	
CREATE FUNCTION rprtr_AvailableDictionary_select
(
)
RETURNS TABLE (
	 DictionaryId BIGINT
	,DictionaryName TEXT
	,CreatedBy TEXT
	,Description TEXT
	,DefinitionUpdatedOn TIMESTAMP WITH TIME ZONE
	,FileUpdatedOn TIMESTAMP WITH TIME ZONE
	,VersionId BIGINT
	,CurrentDictionaryVersion INTEGER
)
AS $$
BEGIN
	RETURN QUERY
	SELECT	 def.Id AS DictionaryId
			,def.DisplayName AS DictionaryName
			,u.UserName AS CreatedBy
			,def.Description AS Description
			,def.ModifiedOn AS DefinitionUpdatedOn
			,ver.ModifiedOn AS FileUpdatedOn
			,ver.Id AS VersionId
			,ver.DictionaryVersion AS CurrentDictionaryVersion
	FROM DictionaryDefinition AS def
	LEFT JOIN RepUser u ON def.OwnerRepUserId = u.Id
	LEFT JOIN LatestDictionaryVersion ver ON def.Id = ver.DictionaryDefinitionId
	WHERE def.VisibilitySettingId = 0;
END;
$$ LANGUAGE plpgsql;