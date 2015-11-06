@echo off

set DBGEN_HOME=.

rem
rem Encryption.
rem
%DBGEN_HOME%\run.bat -xDBGen.xls -elv84g@Kt3d*B9kls0f;?235f -ddialect.hsqldb

rem
rem No encryption.
rem
rem %DBGEN_HOME%\run.bat -xDBGen.xls -elv84g@Kt3d*B9kls0f;?235f -ddialect.mssql -ddialect.oracle
