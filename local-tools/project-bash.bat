@echo off

SET CURRENTDIR="%~dp0"
call %CURRENTDIR%\project-setenv.bat

start %BASH_HOME%\git-bash.exe

exit