@ECHO OFF
set REQUIRED_JAVA_VERSION=1.8.0_100

ECHO Required java version %REQUIRED_JAVA_VERSION%


REM split the version details
for /F "tokens=1,2,3,4 delims=._" %%a in ("%REQUIRED_JAVA_VERSION%") do (
set REQUIRED_MAJOR_VERSION=%%a
set REQUIRED_MINOR_VERSION=%%b
set REQUIRED_REST_VERSION=%%c
set REQUIRED_RELEASE_UPDATE_VERSION=%%d
)
REM ECHO %REQUIRED_MAJOR_VERSION%
REM ECHO %REQUIRED_MINOR_VERSION%
REM ECHO %REQUIRED_REST_VERSION%
REM ECHO %REQUIRED_RELEASE_UPDATE_VERSION%


REM capture the current java version
for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    	set CURRENT_JAVA_VERSION=%%g
)
set CURRENT_JAVA_VERSION=%CURRENT_JAVA_VERSION:"=%
ECHO Current java version %CURRENT_JAVA_VERSION%

REM split the version details
for /F "tokens=1,2,3,4 delims=._" %%a in ("%CURRENT_JAVA_VERSION%") do (
set CURRENT_MAJOR_VERSION=%%a
set CURRENT_MINOR_VERSION=%%b
set CURRENT_REST_VERSION=%%c
set CURRENT_RELEASE_UPDATE_VERSION=%%d
)
REM ECHO %CURRENT_MAJOR_VERSION%
REM ECHO %CURRENT_MINOR_VERSION%
REM ECHO %CURRENT_REST_VERSION%
REM ECHO %CURRENT_RELEASE_UPDATE_VERSION%

If %REQUIRED_MAJOR_VERSION% NEQ %CURRENT_MAJOR_VERSION% GOTO checkmajorversionerror
If %REQUIRED_MINOR_VERSION% NEQ %CURRENT_MINOR_VERSION% GOTO checkminorversionerror
If %REQUIRED_REST_VERSION% NEQ %CURRENT_REST_VERSION% GOTO checkrestversionerror
If %REQUIRED_RELEASE_UPDATE_VERSION% GTR %CURRENT_RELEASE_UPDATE_VERSION% GOTO checkrreleaseupdateversionerror


REM ECHO Versions are good
set ERRORLEVEL=0
EXIT /B 0


:checkmajorversionerror
ECHO major version mismatch
set ERRORLEVEL=1
EXIT /B %ERRORLEVEL%

:checkminorversionerror
ECHO minor version mismatch
set ERRORLEVEL=1
EXIT /B %ERRORLEVEL%

:checkrestversionerror
ECHO rest version mismatch
set ERRORLEVEL=1
EXIT /B %ERRORLEVEL%

:checkrreleaseupdateversionerror
ECHO required release update version is more than the current
set ERRORLEVEL=1
EXIT /B %ERRORLEVEL%

