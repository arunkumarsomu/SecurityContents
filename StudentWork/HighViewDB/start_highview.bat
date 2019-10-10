@ECHO OFF 
DIR *.* 2>NUL | FIND /I "server.properties" >NUL 

IF ERRORLEVEL 1 GOTO WRONG_DIRECTORY
IF NOT ERRORLEVEL 1 GOTO START_DB 

:WRONG_DIRECTORY
  ECHO HighView database should be started from the StudentWork\HighViewDB directory
  @ECHO OFF
GOTO END

:START_DB

java -cp hsqldb.jar org.hsqldb.Server -database ./data/highview -trace true -silent false
GOTO END


:END
