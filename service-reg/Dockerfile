FROM openjdk:17-slim

WORKDIR /app

# Use a relative path to the target directory (without the leading '/')
COPY ./target/service-reg-0.0.1-SNAPSHOT.jar /app/service-reg.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/service-reg.jar"]

