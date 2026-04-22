@echo off
echo.
echo === COMPILANDO MICROSERVICIOS ===
echo.
call cd C:\teatromoro\ms-ticketing
call mvn clean install -U
call cd C:\teatromoro\ms-reserva
call mvn clean install -U
call cd C:\teatromoro\ms-pagos
call mvn clean install -U
call cd C:\teatromoro\ms-suscripciones
call mvn clean install -U
call cd C:\teatromoro\ms-catalogo
call mvn clean install -U
call cd C:\teatromoro\ms-funciones
call mvn clean install -U
call cd C:\teatromoro\ms-gestion
call mvn clean install -U
call cd C:\teatromoro\ms-notificaciones
call mvn clean install -U
call cd C:\teatromoro\ms-feedback
call mvn clean install -U
call cd C:\teatromoro\ms-cafeteria
call mvn clean install -U
call cd C:\teatromoro\ms-merchantdising
call mvn clean install -U
call cd C:\teatromoro\ms-personal
call mvn clean install -U
call cd C:\teatromoro\ms-informes
call mvn clean install -U
call cd C:\teatromoro\ms-promociones
call mvn clean install -U
call cd C:\teatromoro\ms-usuarios
call mvn clean install -U
echo.
echo === COMPILACION COMPLETADA ===
pause
