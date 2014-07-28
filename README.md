returntobasics
==============

#Getting started
## Maven
* Set environment variables, either every time you open the command prompt or once at  
Start->Right click on Computer, choose Properties->Advanced system settings->Environment variables
  * JAVA_HOME=<location of the installed jdk, e.g. C:\Program Files\Java\jdk1.7.0_25>
  * MAVEN_HOME=<location of the maven installation>
  * PATH=%PATH%;%MAVEN_HOME%\bin
* (COMMAND) Verify by *mvn -version*


## Eclipse workspace
* Unpack local-tools/returntobasics-workspace.zip to E:\gaston\workspaces
* (Command) mvn -Declipse.workspace="E:\gaston\workspaces\returntobasics" eclipse:configure-workspace
* (Command) mvn eclipse:eclipse -DdownloadJavadocs=true -DdownloadSources=true
* (Eclipse) Open workspace E:\gaston\workspaces\returntobasics
* (Eclipse) Import existing projects from E:\gaston\git\returntobasics


#Generating site documentation
## Markdown basics tutorial
https://help.github.com/articles/markdown-basics

