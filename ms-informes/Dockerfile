FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9013
ENTRYPOINT ["java","-jar","app.jar"]