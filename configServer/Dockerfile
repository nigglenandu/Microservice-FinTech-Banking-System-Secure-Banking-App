FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/configServer-0.0.1-SNAPSHOT.jar /app/config-server.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "/app/config-server.jar"]