FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia el JAR
COPY target/*.jar app.jar

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "-Dserver.port=3000", "app.jar"]