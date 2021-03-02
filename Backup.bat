@ECHO OFF
ECHO ²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²
ECHO.
ECHO                             INICIANDO BACKUP                      
ECHO                                
ECHO.
ECHO ²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²
ECHO. 

@ECHO.

SET DATA=%date:~6,4%_%date:~3,2%_%date:~0,2%
SET MYSQLDUMP="C:\Program Files\MySQL\MySQL Server 5.7\bin\mysqldump.exe"
SET BACKUP_DIR=C:\Backup
SET BACKUP_NAME=sys_cafe-%DATA%.sql

if not exist %BACKUP_DIR% mkdir %BACKUP_DIR%

REM SET PENDRIVE=
REM SET DROPBOX=C:\DROPBOX\CLIENTE_NOME

%MYSQLDUMP% --add-drop-table --add-locks --create-options --disable-keys --port=3307 --extended-insert --quick --set-charset -F -u root --password= --databases sys_cafe > %BACKUP_DIR%/%BACKUP_NAME%


REM XCOPY %DIRLOCAL%\BKP.RAR %DIRLOCAL%\%FILENAME% /Y
REM XCOPY %DIRLOCAL%\%FILENAME% %PENDRIVE%\ /Y
REM XCOPY %DIRLOCAL%\%FILENAME% %DROPBOX%\ /Y

ECHO.
ECHO ²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²
ECHO.
ECHO                         COPIA DE SEGURANCA CONCLUIDA!             
ECHO.
ECHO ²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²
ECHO.

PAUSE
