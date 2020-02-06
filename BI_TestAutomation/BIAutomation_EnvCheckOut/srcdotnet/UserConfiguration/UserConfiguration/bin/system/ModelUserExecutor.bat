@ECHO OFF
set logfileforthisexecution=%~n0
set current_folder=%~dp0
set project_home=%currentfolder%..\


call %currentfolder%..\system\javaver.bat
If %ERRORLEVEL% NEQ 0 GOTO Error


REM echo %project_home%


set classpath=%project_home%bin;%project_home%lib\javamail_1_5_5\*;%project_home%lib\selenium-2.52.0\*;%project_home%lib\selenium-2.52.0\libs\*;%project_home%lib\log4j_2_1\*;
echo %classpath%
java org.testng.TestNG %current_folder%ModelUserTest.xml>%logfileforthisexecution%_log.txt

set ERRORLEVEL=0
EXIT /B %ERRORLEVEL%



:error
set ERRORLEVEL=1
ECHO Error
EXIT /B %ERRORLEVEL%
