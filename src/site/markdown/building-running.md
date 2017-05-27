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
mvn -N com.oracle.weblogic:weblogic-maven-plugin:create-domain

**Start the server**  
mvn -N com.oracle.weblogic:weblogic-maven-plugin:start-server

**Deploy the application**  
mvn -N com.oracle.weblogic:weblogic-maven-plugin:deploy -Dsource=rtb/rtb-ear/target/rtb-ear-1.0-SNAPSHOT.ear
mvn -N com.oracle.weblogic:weblogic-maven-plugin:deploy -Dsource=rtb/rtb-web/target/rtb-web-1.0-SNAPSHOT.war

**Re-deploy the application**  
mvn -N com.oracle.weblogic:weblogic-maven-plugin:redeploy -Dsource=rtb-ear/target/rtb-ear-1.0-SNAPSHOT.ear -Dname=rtb-ear-1.0-SNAPSHOT

**Stop the server**  
mvn -N com.oracle.weblogic:weblogic-maven-plugin:stop-server

