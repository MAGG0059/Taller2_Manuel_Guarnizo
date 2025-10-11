FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia el JAR específico
COPY target/taller2-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "-Dserver.port=3000", "app.jar"]