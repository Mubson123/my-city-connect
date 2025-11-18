# Build stage
FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jdk-alpine AS runner

WORKDIR /app

# Copy the built jar from build stage
COPY --from=builder ./app/target/api-gateway-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the application port
EXPOSE 9098

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
