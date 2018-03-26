DELIMITER //
CREATE PROCEDURE rprtr_AvailableDeck_Select
(
	 p_PageNumber INTEGER			-- Page number * page size
	,p_PageSize INTEGER				-- Page size
	,p_NameSearchTerm VARCHAR(256)		-- What deck name to look for
	,p_CreatorSearchTerm VARCHAR(256)	-- What author name to look for
)
BEGIN
SET @p_rowOffset = (p_PageNumber * p_PageSize);
SET @p_PageNumber = p_PageNumber;
SET @p_PageSize = p_PageSize;
SET @p_NameSearchTerm = p_NameSearchTerm;
SET @p_CreatorSearchTerm = p_CreatorSearchTerm;
PREPARE stmt FROM "
	SELECT	 DeckId
			,DeckName
			,CreatedBy
			,Description
			,DefinitionUpdatedOn
			,FileUpdatedOn
			,VersionId
			,CurrentVersion
	FROM AvailableDeck
	WHERE	DeckName LIKE CONCAT('%',?,'%')
	AND		CreatedBy LIKE CONCAT('%',?,'%')
	LIMIT ?, ?";
	EXECUTE stmt USING @p_NameSearchTerm, @p_CreatorSearchTerm, @p_rowOffset, @p_PageSize;
	DEALLOCATE PREPARE stmt;
END
//
DELIMITER ;