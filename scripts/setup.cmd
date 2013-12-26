@echo off

REM ========= BEGIN OF PARAMETERS AREA ====================
SET TravelDreamPrefix=TravelDreamX

SET DOMAINADMINPORT=12345
SET DOMAININSTANCEPORT=8888

SET asadmin_dir=C:/glassfish4/bin/
SET mySql_dir=C:/Program Files/MySQL/MySQL Server 5.6/bin/

REM ========= END OF PARAMETERS AREA ======================

SET TravelDreamDBNAME=%TravelDreamPrefix%db
SET TravelDreamDBUSER=%TravelDreamPrefix%dbu
SET TravelDreamDBPASSWORD=%TravelDreamPrefix%dbpsw
SET TravelDreamDOMAIN=%TravelDreamPrefix%domain
SET TravelDreamCONNPOOL=%TravelDreamPrefix%cp
SET TravelDreamJDBC=jdbc/%TravelDreamPrefix%


SET mySql_exe=mysql.exe
SET asadmin_exe=asadmin.bat

SET asadmin=%asadmin_dir%%asadmin_exe% --port %DOMAINADMINPORT% --echo=false

SET createUserQuery=GRANT ALL PRIVILEGES ON %TravelDreamDBNAME%.* TO '%TravelDreamDBUSER%'@'localhost' IDENTIFIED BY '%TravelDreamDBPASSWORD%'
SET createDBquery=DROP DATABASE IF EXISTS %TravelDreamDBNAME%; CREATE DATABASE %TravelDreamDBNAME%; SHOW DATABASES; 

echo **** TravelDreamX by DN, LR, AR (2013) @ PoliMI (Softw. Eng. 2) ****
echo.
echo             STEP 1, MYSQL CONFIGURATION
echo.
echo == Creating the database user, please insert the mysql root psw == 
"%mySql_dir%%mySql_exe%" -u root -p mysql -e "%createUserQuery%"
echo %createUserQuery%
echo.
echo == Creating the schema == 
"%mySql_dir%%mySql_exe%" -u %TravelDreamDBUSER% -p%TravelDreamDBPASSWORD% -e "%createDBquery%"

echo.
echo             SETP 2, GLASSFISH CONFIGURATION
echo.
REM "%asadmin_dir%%asadmin_exe%" multimode --file res/4glassfish.txt --echo=true --port 12345
echo == Starting the JAVADB database == 
call %asadmin% start-database
echo.
echo == Stopping the TravelDream domain (if it's running) == 
call %asadmin% stop-domain %TravelDreamDOMAIN%
echo.
echo == Removing the existing TravelDream domain (created with this setup) == 
echo %asadmin% delete-domain %TravelDreamDOMAIN%
call %asadmin% delete-domain %TravelDreamDOMAIN%
echo.
echo == Creating the TravelDream domain == 
call %asadmin% create-domain --adminport %DOMAINADMINPORT% --nopassword --instanceport %DOMAININSTANCEPORT% %TravelDreamDOMAIN%
echo.
echo == Starting the domain == 
call %asadmin% start-domain %TravelDreamDOMAIN%
echo.
echo == Deleting the existing TravelDream JDBC resources and connection pool == 
call %asadmin% delete-jdbc-resource jdbc/TravelDreamX
call %asadmin% delete-jdbc-connection-pool TravelDreamXcp
echo.
echo == Creating the TravelDream JDBC Connection Pool == 
call %asadmin% create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --property portNumber=3306:password=%TravelDreamDBPASSWORD%:user=%TravelDreamDBUSER%:serverName=localhost:databaseName=%TravelDreamDBNAME% %TravelDreamCONNPOOL%
echo.
echo == Creating the TravelDream JDBC resource == 
call %asadmin% create-jdbc-resource --connectionpoolid %TravelDreamCONNPOOL% %TravelDreamJDBC%
echo.
echo == Deploying TravelDream application == 
call %asadmin% deploy res/traveldream.war
echo.
echo Done!
PAUSE