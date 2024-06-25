# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/customer-0.0.1-SNAPSHOT.jar customer-0.0.1-SNAPSHOT.jar

# Expose the port your application will run on (assuming 8080)
EXPOSE 8080

# Command to run the application when the container starts
CMD ["java", "-jar", "customer-0.0.1-SNAPSHOT.jar"]
