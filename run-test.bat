@echo off
echo ===== Iniciando Eureka Server =====
start "EUREKA" java -jar eureka\target\cl.teatromoro-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=test

timeout /t 5 /nobreak > nul

echo ===== Iniciando Microservicios =====
start "MS-TICKETING" java -jar ms-ticketing\target\cl.teatromoro-ticketing-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-RESERVA" java -jar ms-reserva\target\cl.teatromoro-reserva-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-PAGOS" java -jar ms-pagos\target\cl.teatromoro-pagos-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-SUSCRIPCIONES" java -jar ms-suscripciones\target\cl.teatromoro-suscripciones-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-CATALOGO" java -jar ms-catalogo\target\cl.teatromoro-catalogo-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-FUNCIONES" java -jar ms-funciones\target\cl.teatromoro-funciones-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-GESTION" java -jar ms-gestion\target\cl.teatromoro-gestion-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-NOTIFICACIONES" java -jar ms-notificaciones\target\cl.teatromoro-notificaciones-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-FEEDBACK" java -jar ms-feedback\target\cl.teatromoro-feedback-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-CAFETERIA" java -jar ms-cafeteria\target\cl.teatromoro-cafeteria-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-MERCHANTDISING" java -jar ms-merchantdising\target\cl.teatromoro-merchantdising-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-PERSONAL" java -jar ms-personal\target\cl.teatromoro-personal-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-INFORMES" java -jar ms-informes\target\cl.teatromoro-informes-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-PROMOCIONES" java -jar ms-promociones\target\cl.teatromoro-promociones-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
start "MS-USUARIOS" java -jar ms-usuarios\target\cl.teatromoro-usuarios-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
rem Agrega aqui los demas microservicios si necesitas

echo Todos los servicios han sido lanzados.
