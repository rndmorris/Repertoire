#!/bin/bash
echo "Creating database repertoire";

SCRIPT_DIRECTORY=$(dirname $(readlink -f $0));

mysql --execute="DROP DATABASE IF EXISTS Repertoire;";
mysql --execute="CREATE DATABASE Repertoire;";

echo "Creating the base relations, foreign keys, and views";
mysql --database=Repertoire --execute="source $SCRIPT_DIRECTORY/00_create_Repertoire.sql;";

echo "Creating functions";
mysql --database=Repertoire --execute="source $SCRIPT_DIRECTORY/sprocs/rprtr_DictionaryDefinition_AddNew.sql;";
mysql --database=Repertoire --execute="source $SCRIPT_DIRECTORY/sprocs/rprtr_AvailableDictionary_Select.sql;";

echo "Loading test data";
mysql --database=Repertoire --execute="source $SCRIPT_DIRECTORY/01_populate_data.sql;"

echo "Creating SQL Users"
mysql --database=Repertoire --execute="source $SCRIPT_DIRECTORY/02_create_users.sql;";
