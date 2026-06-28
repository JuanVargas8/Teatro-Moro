@echo off
echo.
echo === REINSTALACION DE DEPENDENCIAS MAVEN ===
echo.

REM Paso 1: Eliminar carpeta local de dependencias
echo Eliminando carpeta .m2 ...
rmdir /s /q %USERPROFILE%\.m2

REM Paso 2: Eliminar carpetas target de los proyectos
echo Eliminando carpetas target ...

rmdir /s /q C:\teatromoro\eureka\target
rmdir /s /q C:\teatromoro\api-gateway\target
rmdir /s /q C:\teatromoro\ms-ticketing\target
rmdir /s /q C:\teatromoro\ms-reserva\target
rmdir /s /q C:\teatromoro\ms-pagos\target
rmdir /s /q C:\teatromoro\ms-suscripciones\target
rmdir /s /q C:\teatromoro\ms-catalogo\target
rmdir /s /q C:\teatromoro\ms-funciones\target
rmdir /s /q C:\teatromoro\ms-gestion\target
rmdir /s /q C:\teatromoro\ms-notificaciones\target
rmdir /s /q C:\teatromoro\ms-feedback\target
rmdir /s /q C:\teatromoro\ms-cafeteria\target
rmdir /s /q C:\teatromoro\ms-merchantdising\target
rmdir /s /q C:\teatromoro\ms-personal\target
rmdir /s /q C:\teatromoro\ms-informes\target
rmdir /s /q C:\teatromoro\ms-promociones\target
rmdir /s /q C:\teatromoro\ms-usuarios\target

REM Paso 3: Instalar todas las dependencias forzadamente
echo Descargando dependencias nuevamente con Maven ...
mvn clean install -U -DskipTests

echo.
echo === PROCESO COMPLETADO ===
pause