# Use a base image with Maven and Java 17 pre-installed
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Copy the Maven project definition file(s) into the container
COPY pom.xml .
COPY src/ src/

# Download the dependencies and build the application
RUN mvn dependency:go-offline
RUN mvn clean package

# Use a lightweight base image with Java 17 pre-installed
FROM maven:3.9.6-eclipse-temurin-17

# Copy the application JAR file from the previous stage
COPY --from=build target/heliosx-consultation-0.0.1-SNAPSHOT.jar .
# Command to run your application when the container starts
CMD ["java", "-jar", "heliosx-consultation-0.0.1-SNAPSHOT.jar"]
