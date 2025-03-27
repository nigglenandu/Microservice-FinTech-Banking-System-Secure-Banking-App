FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Transaction-History-0.0.1-SNAPSHOT.jar /app/Transaction-History

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "/app/Transaction-History"]