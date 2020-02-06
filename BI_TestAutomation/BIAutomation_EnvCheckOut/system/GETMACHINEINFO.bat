@echo off

set mainfile=%0.txt

if exist %mainfile% del %mainfile%
systeminfo >> %mainfile%
REM systeminfo | FIND /I "DOMAIN:" >> %mainfile%
REM systeminfo | FIND /I "HOST NAME:" >> %mainfile%
REM systeminfo | FIND /I "OS Name:" >> %mainfile%
REM systeminfo | FIND /I "OS Version:" >> %mainfile%