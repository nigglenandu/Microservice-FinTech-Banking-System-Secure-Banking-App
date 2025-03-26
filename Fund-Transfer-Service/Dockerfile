FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Fund-Transfer-Service-0.0.1-SNAPSHOT.jar /app/Fund-Transfer-Service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/Fund-Transfer-Service.jar"]
