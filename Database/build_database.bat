@SETLOCAL EnableDelayedExpansion EnableExtensions
@ECHO OFF

set MYSQL=""
IF "%MYSQL%" == "" GOTO NO_MYSQL

set SCRIPT_DIRECTORY=%~dp0
set /p MYSQL_USER="MySQL Username: "
set /p MYSQL_PASSWORD="MySQL Password: "

ECHO "Creating database repertoire"

%MYSQL% --user=%MYSQL_USER% --password=%MYSQL_PASSWORD% --execute="DROP DATABASE IF EXISTS Repertoire;";
%MYSQL% --user=%MYSQL_USER% --password=%MYSQL_PASSWORD% --execute="CREATE DATABASE Repertoire;";

ECHO "Creating the base relations, foreign keys, and views";
%MYSQL% --user=%MYSQL_USER% --password=%MYSQL_PASSWORD% --database=Repertoire --execute="source %SCRIPT_DIRECTORY%\00_create_Repertoire.sql;";

ECHO "Creating functions";
%MYSQL% --user=%MYSQL_USER% --password=%MYSQL_PASSWORD% --database=Repertoire --execute="source %SCRIPT_DIRECTORY%\sprocs\rprtr_DictionaryDefinition_AddNew.sql;";
%MYSQL% --user=%MYSQL_USER% --password=%MYSQL_PASSWORD% --database=Repertoire --execute="source %SCRIPT_DIRECTORY%\sprocs\rprtr_DictionaryVersion_AddNew.sql;";
%MYSQL% --user=%MYSQL_USER% --password=%MYSQL_PASSWORD% --database=Repertoire --execute="source %SCRIPT_DIRECTORY%\sprocs\rprtr_AvailableDictionary_Select.sql;";

ECHO "Loading test data";
%MYSQL% --user=%MYSQL_USER% --password=%MYSQL_PASSWORD% --database=Repertoire --execute="source %SCRIPT_DIRECTORY%\01_populate_data.sql;"

ECHO "Creating SQL Users"
%MYSQL% --user=%MYSQL_USER% --password=%MYSQL_PASSWORD% --database=Repertoire --execute="source %SCRIPT_DIRECTORY%\02_create_users.sql;";

GOTO EXIT

:NO_MYSQL
ECHO Please edit the script and set the "MYSQL" variable to the path to mysql.exe
GOTO EXIT

:EXIT
PAUSE

ENDLOCAL
