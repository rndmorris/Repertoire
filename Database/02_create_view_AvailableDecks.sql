CREATE VIEW Repertoire.AvailableDecks
AS SELECT	 def.Id AS 'DeckId'
			,def.DisplayName AS 'DeckName'
			,u.UserName AS 'CreatedBy'
			,def.Description AS 'Description'
			,ver.Version AS 'DeckVersion'
			,ver.ModifiedOn AS 'LastUpdated'
			,ver.FilePath AS 'DownloadURL'
FROM DeckDefinition AS def LEFT JOIN (User AS u, DeckVersion AS ver)
	ON (def.OwnerUserId = u.Id AND def.DeckVersionId = ver.Id)
WHERE def.VisibilitySettingId = 0;