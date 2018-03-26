#!/bin/bash
echo "Creating database repertoire";

SCRIPT_DIRECTORY=$(dirname $(readlink -f $0));

mysql --execute="DROP DATABASE Repertoire;";
mysql --execute="CREATE DATABASE Repertoire;";

echo "Creating the base relations, foreign keys, and views";
mysql --database=Repertoire --execute="source $SCRIPT_DIRECTORY/00_create_Repertoire.sql;";

echo "Creating functions";
mysql --database=Repertoire --execute="source $SCRIPT_DIRECTORY/sprocs/rprtr_DeckDefinition_AddNew.sql;";
mysql --database=Repertoire --execute="source $SCRIPT_DIRECTORY/sprocs/rprtr_DeckVersion_AddNew.sql;";
mysql --database=Repertoire --execute="source $SCRIPT_DIRECTORY/sprocs/rprtr_AvailableDeck_Select.sql;";

echo "Loading test data";
mysql --database=Repertoire --execute="source $SCRIPT_DIRECTORY/01_populate_Repertoire.sql;"

