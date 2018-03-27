SELECT '' AS 'Populating SQL User';
DROP USER IF EXISTS 'RepertoireWebAPI'@'localhost';
CREATE USER	'RepertoireWebAPI'@'localhost' IDENTIFIED with mysql_native_password;
SET PASSWORD FOR 'RepertoireWebAPI'@'localhost' = PASSWORD('testPassword');

GRANT SELECT ON Repertoire.* TO 'RepertoireWebAPI'@'localhost';
GRANT EXECUTE ON Repertoire.* TO 'RepertoireWebAPI'@'localhost';