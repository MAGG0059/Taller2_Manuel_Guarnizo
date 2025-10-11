FROM openjdk:17-jdk-slim

WORKDIR /app
COPY target/*.jar app.jar


HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:3000/actuator/health || exit 1

EXPOSE 3000
ENTRYPOINT ["java", "-jar", "-Dserver.port=3000", "app.jar"]