# Getting started
## Maven
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