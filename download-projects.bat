@echo off
echo Descargando microservicios Spring Boot...
echo.
echo Descargando eureka.zip...
curl -o eureka.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=eureka&groupId=cl.teatromoro&artifactId=cl-teatromoro-eureka&name=teatromoro-eureka&description=servicio-eureka&packageName=cl.teatromoro.eureka&packaging=jar&javaVersion=21&dependencies=cloud-eureka-server,devtools"
echo.
echo Descargando ms-ticketing.zip...
curl -o ms-ticketing.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-ticketing&groupId=cl.teatromoro&artifactId=cl-teatromoro-ticketing&name=teatromoro-ticketing&description=servicio-ticketing&packageName=cl.teatromoro.ticketing&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-reserva.zip...
curl -o ms-reserva.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-reserva&groupId=cl.teatromoro&artifactId=cl-teatromoro-reserva&name=teatromoro-reserva&description=servicio-reserva&packageName=cl.teatromoro.reserva&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-pagos.zip...
curl -o ms-pagos.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-pagos&groupId=cl.teatromoro&artifactId=cl-teatromoro-pagos&name=teatromoro-pagos&description=servicio-pagos&packageName=cl.teatromoro.pagos&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-suscripciones.zip...
curl -o ms-suscripciones.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-suscripciones&groupId=cl.teatromoro&artifactId=cl-teatromoro-suscripciones&name=teatromoro-suscripciones&description=servicio-suscripciones&packageName=cl.teatromoro.suscripciones&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-catalogo.zip...
curl -o ms-catalogo.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-catalogo&groupId=cl.teatromoro&artifactId=cl-teatromoro-catalogo&name=teatromoro-catalogo&description=servicio-catalogo&packageName=cl.teatromoro.catalogo&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-funciones.zip...
curl -o ms-funciones.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-funciones&groupId=cl.teatromoro&artifactId=cl-teatromoro-funciones&name=teatromoro-funciones&description=servicio-funciones&packageName=cl.teatromoro.funciones&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-gestion.zip...
curl -o ms-gestion.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-gestion&groupId=cl.teatromoro&artifactId=cl-teatromoro-gestion&name=teatromoro-gestion&description=servicio-gestion&packageName=cl.teatromoro.gestion&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-notificaciones.zip...
curl -o ms-notificaciones.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-notificaciones&groupId=cl.teatromoro&artifactId=cl-teatromoro-notificaciones&name=teatromoro-notificaciones&description=servicio-notificaciones&packageName=cl.teatromoro.notificaciones&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-feedback.zip...
curl -o ms-feedback.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-feedback&groupId=cl.teatromoro&artifactId=cl-teatromoro-feedback&name=teatromoro-feedback&description=servicio-feedback&packageName=cl.teatromoro.feedback&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-cafeteria.zip...
curl -o ms-cafeteria.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-cafeteria&groupId=cl.teatromoro&artifactId=cl-teatromoro-cafeteria&name=teatromoro-cafeteria&description=servicio-cafeteria&packageName=cl.teatromoro.cafeteria&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-merchantdising.zip...
curl -o ms-merchantdising.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-merchantdising&groupId=cl.teatromoro&artifactId=cl-teatromoro-merchantdising&name=teatromoro-merchantdising&description=servicio-merchantdising&packageName=cl.teatromoro.merchantdising&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-personal.zip...
curl -o ms-personal.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-personal&groupId=cl.teatromoro&artifactId=cl-teatromoro-personal&name=teatromoro-personal&description=servicio-personal&packageName=cl.teatromoro.personal&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-informes.zip...
curl -o ms-informes.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-informes&groupId=cl.teatromoro&artifactId=cl-teatromoro-informes&name=teatromoro-informes&description=servicio-informes&packageName=cl.teatromoro.informes&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-promociones.zip...
curl -o ms-promociones.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-promociones&groupId=cl.teatromoro&artifactId=cl-teatromoro-promociones&name=teatromoro-promociones&description=servicio-promociones&packageName=cl.teatromoro.promociones&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descargando ms-usuarios.zip...
curl -o ms-usuarios.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.5.13&baseDir=ms-usuarios&groupId=cl.teatromoro&artifactId=cl-teatromoro-usuarios&name=teatromoro-usuarios&description=servicio-usuarios&packageName=cl.teatromoro.usuarios&packaging=jar&javaVersion=21&dependencies=web,data-jpa,lombok,postgresql,cloud-feign"
echo.
echo Descarga completada.
pause
