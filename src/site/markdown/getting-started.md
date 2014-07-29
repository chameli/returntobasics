# Getting started

## Maven
### Settings.xml
* Make a copy of HOME/.m2/settings.xml
* Make the original settings.xml easier to read by removing all commented text and empty elements
* Add the content as seen in local-tools/maven-settings.xml

### In a shell
* Set environment variables, either every time you open the command prompt or once at  
Start->Right click on Computer, choose Properties->Advanced system settings->Environment variables
  * JAVA_HOME=*location of the installed jdk, e.g. C:\Program Files\Java\jdk1.7.0_25*
  * MAVEN_HOME=*location of the maven installation*
  * PATH=%PATH%;%MAVEN_HOME%\bin
* (COMMAND) Verify by *mvn -version*

## Eclipse workspace
### Configuration
Install the following plugins using the listed update sites
* Markdown Editor plugin for Eclipse (http://winterwell.com/software/updatesite/)
* GitHub Flavored Markdown viewer plugin 1.8.3 (https://raw.github.com/satyagraha/gfm_viewer/master/p2-composite/)

### Workspace
* Unpack local-tools/returntobasics-workspace.zip to E:\gaston\workspaces
* (Command) mvn -Declipse.workspace="E:\gaston\workspaces\returntobasics" eclipse:configure-workspace
* (Command) mvn eclipse:eclipse
* (Eclipse) Open workspace E:\gaston\workspaces\returntobasics
* (Eclipse) Import existing projects from E:\gaston\git\returntobasics
* (Eclipse) Select all projects, right click and choose Team->Share->Git

## Weblogic
### Maven plugin
Follow the steps in section **Configuring the WebLogic Development Maven Plug-In - For apache-maven-3.0.x** at  
http://docs.oracle.com/cd/E24329_01/web.1211/e24368/maven.htm#CHEIHIEH

