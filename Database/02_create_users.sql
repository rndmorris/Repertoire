SELECT '' AS 'Populating SQL User';
CREATE USER
	'RepertoireWebAPI'@'localhost'
		IDENTIFIED with mysql_native_password;
SET PASSWORD FOR 'RepertoireWebAPI'@'localhost' = PASSWORD('testPassword');