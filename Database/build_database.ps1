Param(
	[Parameter(Mandatory=$True,Position=1)]
	[System.IO.FileInfo]$MySQLPath
)

if ($MySQLPath.Exists -ne $True) {
	Write-Output 'Please pass in the path to mysql.exe';
	return;
}

$scriptDirectory = (Split-Path -Parent -Path $MyInvocation.MyCommand.Definition);
$mysqlUser = Read-Host 'MySQL Username: ';
$mysqlSecurePassword = Read-Host -AsSecureString 'MySQL Password: ';
$BSTR = [System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($mysqlSecurePassword);
$mysqlPassword = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto($BSTR);

Write-Output 'Creating database repertoire';

& $MySQLPath "--user=$mysqlUser --password=$mysqlPassword --execute=`"DROP DATABASE IF EXISTS Repertoire;`" mysql";
Start-Process -FilePath $MySQLPath.FullName -NoNewWindow -Wait -ArgumentList "--user=$mysqlUser","--password=$mysqlPassword","--execute=`"CREATE DATABASE Repertoire;`"";

Write-Output 'Creating the base relations, foreign keys, and views';
Start-Process -FilePath $MySQLPath.FullName -NoNewWindow -Wait -ArgumentList "--user=$mysqlUser","--password=$mysqlPassword","--database=Repertoire < `"$scriptDirectory00_create_Repertoire.sql`"";

Write-Output 'Creating functions';
Start-Process -FilePath $MySQLPath.FullName -NoNewWindow -Wait -ArgumentList "--user=$mysqlUser","--password=$mysqlPassword","--database=Repertoire < `"$scriptDirectorysprocs\rprtr_DictionaryDefinition_AddNew.sql`"";
Start-Process -FilePath $MySQLPath.FullName -NoNewWindow -Wait -ArgumentList "--user=$mysqlUser","--password=$mysqlPassword","--database=Repertoire < `"$scriptDirectorysprocs\rprtr_DictionaryVersion_AddNew.sql`"";
Start-Process -FilePath $MySQLPath.FullName -NoNewWindow -Wait -ArgumentList "--user=$mysqlUser","--password=$mysqlPassword","--database=Repertoire < `"$scriptDirectorysprocs\rprtr_AvailableDictionary_Select.sql`"";

Write-Output 'Loading test data';
Start-Process -FilePath $MySQLPath.FullName -NoNewWindow -Wait -ArgumentList "--user=$mysqlUser","--password=$mysqlPassword","--database=Repertoire < `"$scriptDirectory01_populate_data.sql`"";

Write-Output 'Creating SQL Users';
Start-Process -FilePath $MySQLPath.FullName -NoNewWindow -Wait -ArgumentList "--user=$mysqlUser","--password=$mysqlPassword","--database=Repertoire < `"$scriptDirectory02_create_users.sql`"";

return;