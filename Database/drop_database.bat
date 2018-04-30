@ECHO OFF
SETLOCAL
set MYSQL="C:\xampp\mysql\bin\mysql.exe"
IF %MYSQL% == "" GOTO NO_MYSQL

set SCRIPT_DIRECTORY=%~dp0
set /p MYSQL_USER="MySQL Username: "
set /p MYSQL_PASSWORD="MySQL Password: "

ECHO Dropping database repertoire

%MYSQL% --user=%MYSQL_USER% --password=%MYSQL_PASSWORD% --database=mysql --execute="DROP DATABASE IF EXISTS Repertoire;";
%MYSQL% --user=%MYSQL_USER% --password=%MYSQL_PASSWORD% --database=mysql --execute="DROP USER IF EXISTS 'RepertoireWebAPI'@'localhost';";

GOTO EXIT

:NO_MYSQL
ECHO Please edit the script and set the "MYSQL" variable to the path to mysql.exe
GOTO EXIT

:EXIT
ENDLOCAL
PAUSE
