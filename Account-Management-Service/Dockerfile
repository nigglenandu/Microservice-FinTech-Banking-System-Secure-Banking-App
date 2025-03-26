# Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the .jar file into the container
COPY target/Account-Management-Service-0.0.1-SNAPSHOT.jar /app/Account-Management-Service.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/Account-Management-Service.jar"]
