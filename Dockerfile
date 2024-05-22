# Stage 1: Build the Spring Boot application
FROM maven:3.9-amazoncorretto-21 AS build

WORKDIR /app

COPY pom.xml /app

RUN mvn verify --fail-never

COPY . /app

RUN mvn package -Dmaven.test.skip

# Stage 2: Create the final image
FROM amazoncorretto:21-alpine-jdk

# Set environment variables
ARG DB_URI

# Set the working directory
WORKDIR /app

# Copy the application JAR file from the build stage
COPY --from=build /app/target/*-SNAPSHOT.jar /app/recommendation-service.jar

# Expose the application port
EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=release", "-jar", "/app/recommendation-service.jar"]