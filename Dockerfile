# Stage 1: Build the Maven application
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Cache dependencies to speed up future builds
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the artifact
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Runtime image (lightweight JRE instead of full JDK)
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built artifact from the builder stage
COPY --from=builder /app/target/*.jar springapi-docker.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "springapi-docker.jar"]