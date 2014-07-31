# Building and running

## Building
**Complete build including unit testing**  
mvn install

**Build of a single project, e.g. rtb-service**  
mvn -pl rtb-service install

**Delete all build artifacts**  
mvn clean

**Complete build but skipping the unit tests**  
mvn install -DskipTests

## Manage database using Liquibase
**Apply pending database changes**  
mvn -N liquibase:update

**Drop all tables**  
mvn -N liquibase:dropAll

**Preview pending database changes**  
mvn -N liquibase:updateSQL  
*SQL file will be written to target/liquibase/migrate.sql*

## Running
**Create a weblogic domain**  
mvn wls:wlst -DfileName=local-tools/create-domain.py

**Start the server**  
mvn wls:start-server

**Deploy the application**  
mvn wls:deploy -Dsource=rtb-ear/target/rtb-ear-1.0-SNAPSHOT.ear

**Re-deploy the application**  
mvn wls:redeploy -Dsource=rtb-ear/target/rtb-ear-1.0-SNAPSHOT.ear -Dname=rtb-ear-1.0-SNAPSHOT

**Stop the server**  
mvn wls:stop-server

