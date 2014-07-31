# Building and running

## Building
*Complete build including unit testing*  
mvn install

*Delete all build artifacts*  
mvn clean

## Running
*Create a weblogic domain*  
mvn wls:wlst -DfileName=local-tools/create-domain.py

*Start the server*  
mvn wls:start-server

*Deploy the application*  
mvn wls:deploy -Dsource=rtb-ear/target/rtb-ear-1.0-SNAPSHOT.ear

*Re-deploy the application*  
mvn wls:redeploy -Dsource=rtb-ear/target/rtb-ear-1.0-SNAPSHOT.ear -Dname=rtb-ear-1.0-SNAPSHOT

*Stop the server*  
mvn wls:stop-server

