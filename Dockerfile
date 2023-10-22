# Use an official Maven image as the base image
FROM maven:3.8.3-openjdk-17-slim

# Set the working directory
WORKDIR /app

# Copy the Maven project's pom.xml to the container
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the rest of the application code to the container
COPY src ./src

# Build the Maven project
RUN mvn package

# Define the command to run your application
CMD ["java", "-jar", "target/simple-player-stats-0.0.1-SNAPSHOT.jar"]




