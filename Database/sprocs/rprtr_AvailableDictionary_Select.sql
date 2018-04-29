DELIMITER //
CREATE PROCEDURE rprtr_AvailableDictionary_Select
(
	 p_PageOffset INTEGER			-- Page number * page size
	,p_PageSize INTEGER				-- Page size
	,p_SearchTerms VARCHAR(256)		-- Terms to search for
)
BEGIN
SET @p_rowOffset = (p_PageOffset * p_PageSize);
SET @p_PageOffset = p_PageOffset;
SET @p_PageSize = p_PageSize;



SET @p_NameSearchTerm = p_SearchTerms;
SET @p_CreatorSearchTerm = p_SearchTerms;
PREPARE stmt FROM "
	SELECT	 DictionaryId
			,DictionaryName
			,CreatedBy
			,Description
			,DefinitionUpdatedOn
			,FileUpdatedOn
			,VersionId
			,FilePath
			,CurrentVersion
	FROM AvailableDictionary
	WHERE	DictionaryName REGEXP ?
	OR		CreatedBy REGEXP ?
	LIMIT ?, ?";
	EXECUTE stmt USING @p_NameSearchTerm, @p_CreatorSearchTerm, @p_rowOffset, @p_PageSize;
	DEALLOCATE PREPARE stmt;
END
//
DELIMITER ;