FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copy project files & build
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests


FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy built JAR from previous stage
COPY --from=builder /app/target/*.jar app.jar

# Expose application port
EXPOSE 8081

# Start Spring Boot application
CMD ["java", "-jar", "app.jar"]
