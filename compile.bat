@echo off
echo.
echo === COMPILANDO MICROSERVICIOS ===
echo.
call mvn clean install -U -DskipTests
echo.
echo === COMPILACION COMPLETADA ===
pause
