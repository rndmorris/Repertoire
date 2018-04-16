DELIMITER //
CREATE PROCEDURE rprtr_AvailableDictionary_Select
(
	 p_PageOffset INTEGER			-- Page number * page size
	,p_PageSize INTEGER				-- Page size
	,p_NameSearchTerm VARCHAR(256)		-- What Dictionary name to look for
	,p_CreatorSearchTerm VARCHAR(256)	-- What author name to look for
)
BEGIN
SET @p_rowOffset = (p_PageOffset * p_PageSize);
SET @p_PageOffset = p_PageOffset;
SET @p_PageSize = p_PageSize;
SET @p_NameSearchTerm = p_NameSearchTerm;
SET @p_CreatorSearchTerm = p_CreatorSearchTerm;
PREPARE stmt FROM "
	SELECT	 DictionaryId
			,DictionaryName
			,CreatedBy
			,Description
			,DefinitionUpdatedOn
			,FileUpdatedOn
			,VersionId
			,CurrentVersion
	FROM AvailableDictionary
	WHERE	DictionaryName LIKE CONCAT('%',?,'%')
	OR		CreatedBy LIKE CONCAT('%',?,'%')
	LIMIT ?, ?";
	EXECUTE stmt USING @p_NameSearchTerm, @p_CreatorSearchTerm, @p_rowOffset, @p_PageSize;
	DEALLOCATE PREPARE stmt;
END
//
DELIMITER ;