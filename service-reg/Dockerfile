FROM openjdk:17-slim

WORKDIR /app

COPY /target/service-reg-0.0.1-SNAPSHOT.jar /app/service-reg

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "service-reg"]
