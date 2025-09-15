# # Use OpenJDK 17 with Alpine
# FROM openjdk:17-oracle
#
# VOLUME /tmp
# ARG JAR_FILE=target/dynamicforms-1.0.0.jar
# COPY ${JAR_FILE} app.jar
#
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","/app.jar"]
#
#
# -----------------------
# Stage 1: Build the JAR
# -----------------------
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# -----------------------
# Stage 2: Run the app
# -----------------------
FROM openjdk:17-oracle

# Create app directory
WORKDIR /app

# Copy JAR from the build stage
COPY --from=build /app/target/dynamicforms-*.jar app.jar

# Expose port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
