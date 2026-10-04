# Stage 1: Build the Maven application
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy configuration and source code
COPY pom.xml .
COPY src ./src

# Build package using BuildKit cache for dependencies
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

# Stage 2: Runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built artifact from the builder stage
COPY --from=builder /app/target/*.jar springapi-docker.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "springapi-docker.jar"]