# Dockerfile para Spring Boot/Maven
FROM maven:3.8.6-openjdk-17 AS build

WORKDIR /app

# Copia el pom.xml primero
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente
COPY src ./src

# Compila y crea el JAR
RUN mvn clean package -DskipTests

# Imagen final más liviana
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Azure espera el puerto 3000
EXPOSE 3000

# Comando para ejecutar
ENTRYPOINT ["java", "-jar", "-Dserver.port=3000", "app.jar"]