#!/bin/bash

#========= BEGIN OF PARAMETERS AREA ====================

TravelDreamPrefix=TravelDreamX

DOMAINADMINPORT=12345
DOMAININSTANCEPORT=8888

asadmin_dir="/home/dario/glassfish4/bin/"
mySql_dir= #C:/Program Files/MySQL/MySQL Server 5.6/bin/

#========= END OF PARAMETERS AREA ======================

TravelDreamDBNAME=$TravelDreamPrefix"db"
TravelDreamDBUSER=$TravelDreamPrefix"dbu"
TravelDreamDBPASSWORD=$TravelDreamPrefix"dbpsw"
TravelDreamDOMAIN=$TravelDreamPrefix"domain"
TravelDreamCONNPOOL=$TravelDreamPrefix"cp"
TravelDreamJDBC="jdbc/"$TravelDreamPrefix

mySql_exe=mysql
asadmin_exe=asadmin
asadmin="$asadmin_dir$asadmin_exe --port $DOMAINADMINPORT --echo=false"

createUserQuery="GRANT ALL PRIVILEGES ON $TravelDreamDBNAME.* TO '$TravelDreamDBUSER'@'localhost' IDENTIFIED BY '$TravelDreamDBPASSWORD'"
createDBquery="DROP DATABASE IF EXISTS $TravelDreamDBNAME; CREATE DATABASE $TravelDreamDBNAME; SHOW DATABASES;"

echo "**** TravelDreamX by DN, LR, AR (2013) @ PoliMI (Softw. Eng. 2) ****"
echo
echo "            STEP 1, MYSQL CONFIGURATION"
echo
echo "== Creating the database user, please insert the mysql root psw =="
"$mySql_dir$mySql_exe" -u root -p mysql -e "$createUserQuery"
echo $createUserQuery
echo
echo "== Creating the schema =="

"$mySql_dir$mySql_exe" -u $TravelDreamDBUSER -p$TravelDreamDBPASSWORD -e "$createDBquery"

echo
echo "            SETP 2, GLASSFISH CONFIGURATION"
echo

echo "== Starting the JAVADB database =="
$asadmin start-database
echo
echo "== Stopping the TravelDream domain (if it's running) =="
$asadmin stop-domain $TravelDreamDOMAIN
echo
echo "== Removing the existing TravelDream domain (created with this setup) =="
$asadmin delete-domain $TravelDreamDOMAIN
echo
echo "== Creating the TravelDream domain =="
$asadmin create-domain --adminport $DOMAINADMINPORT --nopassword --instanceport $DOMAININSTANCEPORT $TravelDreamDOMAIN
echo
echo "== Starting the domain =="
$asadmin start-domain $TravelDreamDOMAIN
echo
echo "== Deleting the existing TravelDream JDBC resources and connection pool =="
$asadmin delete-jdbc-resource $TravelDreamJDBC
$asadmin delete-jdbc-connection-pool $TravelDreamCONNPOOL
echo
echo "== Creating the TravelDream JDBC Connection Pool =="
$asadmin create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --property portNumber=3306:password=$TravelDreamDBPASSWORD:user=$TravelDreamDBUSER:serverName=localhost:databaseName=$TravelDreamDBNAME $TravelDreamCONNPOOL
echo
echo "== Creating the TravelDream JDBC resource =="
$asadmin create-jdbc-resource --connectionpoolid $TravelDreamCONNPOOL $TravelDreamJDBC
echo
echo "== Deploying TravelDream application =="
$asadmin deploy res/traveldream.war
echo
echo Done!
