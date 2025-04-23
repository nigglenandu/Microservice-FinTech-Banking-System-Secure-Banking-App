FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Loan-Transfer-Service-0.0.1-SNAPSHOT.jar /app/Loan-Transfer-Service.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "/app/Loan-Transfer-Service.jar"]


