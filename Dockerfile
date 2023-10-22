# Use an official Maven image as the base image
FROM maven:3.8.3-openjdk-17-slim

# Set the working directory
WORKDIR /app

# Copy the Maven project
COPY /home/circleci/.m2/repository/sg/ntu/edu/simple-player-stats/0.0.1-SNAPSHOT/simple-player-stats-0.0.1-SNAPSHOT.jar /app/app.jar

# # Build the Maven project
# RUN mvn clean install

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]