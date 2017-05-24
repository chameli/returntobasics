@echo off

set JAVA_HOME=\Program\jdk8
set MAVEN_HOME=\Program\maven3
set ANT_HOME=\Program\ant1.9

SET CURRENTDIR="%~dp0"
set BASHHOME=\Program\git2

set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%ANT_HOME%\bin;%PATH%

cd %CURRENTDIR%\..
start %BASHHOME%\git-bash.exe

exit