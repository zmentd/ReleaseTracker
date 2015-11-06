@echo off
rem -------------------------------------------------------------------------
rem DbGen Bootstrap Script for Win32
rem -------------------------------------------------------------------------
if "%DBGEN_HOME%" == "" set DBGEN_HOME=.

echo DBGEN_HOME = %DBGEN_HOME%

set DBGEN_JAVA=%DBGEN_HOME%
set cp=%DBGEN_HOME%
set cp=%cp%;%DBGEN_JAVA%\lib\bhm-util.jar
set cp=%cp%;%DBGEN_JAVA%\lib\commons-cli-1.0.jar
set cp=%cp%;%DBGEN_JAVA%\lib\commons-lang-2.1.jar
set cp=%cp%;%DBGEN_JAVA%\lib\dom4j-1.6.1.jar
set cp=%cp%;%DBGEN_JAVA%\lib\freemarker.jar
set cp=%cp%;%DBGEN_JAVA%\lib\geronimo-stax-api_1.0_spec-1.0.jar
set cp=%cp%;%DBGEN_JAVA%\lib\poi-3.7-20101029.jar
set cp=%cp%;%DBGEN_JAVA%\lib\poi-ooxml-3.7-20101029.jar
set cp=%cp%;%DBGEN_JAVA%\lib\poi-ooxml-schemas-3.7-20101029.jar
set cp=%cp%;%DBGEN_JAVA%\lib\log4j.jar
set cp=%cp%;%DBGEN_JAVA%\lib\xmlbeans-2.3.0.jar
set cp=%cp%;%DBGEN_JAVA%\lib\dbgen.jar
rem set cp=%cp%;%DBGEN_JAVA%\build\SMJAVA1.5D\classes
set cp=%cp%;

C:\dev\Java\1.7.0_45\bin\java -Xmx512M -DDBGEN_HOME=%DBGEN_HOME% -classpath %cp% com.bhmi.tools.dbgen.ui.console.DbGenMain %*