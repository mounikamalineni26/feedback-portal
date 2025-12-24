# Use Eclipse Temurin OpenJDK 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for dependency caching)
COPY mvnw .
COPY .mvn/ .mvn
COPY pom.xml .

# Download dependencies offline
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src/ src/

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8080

# Start the Spring Boot app
CMD ["java", "-jar", "target/feedback-portal-0.0.1-SNAPSHOT.jar"]
