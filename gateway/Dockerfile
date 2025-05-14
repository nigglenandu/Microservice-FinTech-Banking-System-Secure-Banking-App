FROM openjdk:17-jdk-slim

EXPOSE "8086:8086"

WORKDIR /app
RUN apt-get update && apt-get install -y curl
COPY target/gateway-0.0.1-SNAPSHOT.jar /app/gateway.jar


ENTRYPOINT ["java", "-jar", "/app/gateway.jar"]