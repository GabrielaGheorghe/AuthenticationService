# Use a base image with Java 11 pre-installed
FROM openjdk:17-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Copy the src folder to the container
COPY src ./src

# Copy the maven wrapper files to the container and make the mvnw file executable
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Build the project with maven
RUN ./mvnw clean install -DskipTests

# Expose port 8080 for the Spring application
EXPOSE 8080

# Set environment variables for the database connection
ENV DB_HOST=authdb \
    DB_PORT=5432 \
    DB_NAME=auth \
    DB_USER=postgres \
    DB_PASSWORD=4224

# Copy the jar file to the container
COPY target/auth-0.0.1-SNAPSHOT.jar .

# Start the Spring application
CMD ["java", "-jar", "auth-0.0.1-SNAPSHOT.jar"]
