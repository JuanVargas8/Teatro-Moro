@echo off
echo ===== Iniciando Eureka Server =====
start "eureka" mvn -f eureka spring-boot:run

timeout /t 5 /nobreak > nul

echo ===== Iniciando API Gateway =====
start "api-gateway" mvn -f api-gateway spring-boot:run

echo ===== Iniciando Microservicios =====
start "ms-ticketing" mvn -f ms-ticketing spring-boot:run

start "ms-reserva" mvn -f ms-reserva spring-boot:run

start "ms-pagos" mvn -f ms-pagos spring-boot:run

start "ms-suscripciones" mvn -f ms-suscripciones spring-boot:run

start "ms-catalogo" mvn -f ms-catalogo spring-boot:run

start "ms-funciones" mvn -f ms-funciones spring-boot:run

start "ms-gestion" mvn -f ms-gestion spring-boot:run

start "ms-notificaciones" mvn -f ms-notificaciones spring-boot:run

start "ms-personal" mvn -f ms-personal spring-boot:run

start "ms-informes" mvn -f ms-informes spring-boot:run

start "ms-promociones" mvn -f ms-promociones spring-boot:run

start "ms-usuarios" mvn -f ms-usuarios spring-boot:run
rem Agrega aqui los demas microservicios si necesitas

echo Todos los servicios han sido lanzados.
