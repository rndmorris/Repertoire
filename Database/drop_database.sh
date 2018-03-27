#!/bin/bash
mysql --execute="DROP DATABASE IF EXISTS Repertoire;";
mysql --execute="DROP USER IF EXISTS 'RepertoireWebAPI'@'localhost';";