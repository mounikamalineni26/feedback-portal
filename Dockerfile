# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for caching)
COPY mvnw .
COPY .mvn/ .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the code
COPY src/ src/

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose the port Spring Boot will run on
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/feedback-portal-0.0.1-SNAPSHOT.jar"]
