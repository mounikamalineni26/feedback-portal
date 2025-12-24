# Use Eclipse Temurin OpenJDK 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for caching)
COPY mvnw .
COPY .mvn/ .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies (cached)
RUN ./mvnw dependency:go-offline -B

# Copy only the source code (after dependencies cached)
COPY src/ src/

# Build the project without running tests
RUN ./mvnw clean package -DskipTests

# Expose Spring Boot port
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "target/feedback-portal-0.0.1-SNAPSHOT.jar"]
