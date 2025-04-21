FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Transaction-History-Service-0.0.1-SNAPSHOT.jar /app/Transaction-History-Service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/Transaction-History-Service.jar"]
