FROM openjdk:17-jdk-slim

COPY target/Gestao-Hospital-0.0.1-SNAPSHOT.jar   /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]