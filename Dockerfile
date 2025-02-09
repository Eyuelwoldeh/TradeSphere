# Step 1: Build the application using Maven
FROM maven:3.8.4-openjdk-11-slim AS build
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies (avoid copying the whole code at first to leverage Docker's caching mechanism)
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Step 2: Copy the whole project into the Docker image
COPY src ./src

# Step 3: Package the application
RUN mvn clean package -DskipTests

# Step 4: Run the Spring Boot JAR file
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port (you can change it if necessary)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]