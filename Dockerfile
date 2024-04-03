# Use a base image with Maven and Java 17 pre-installed
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set the working directory inside the container
VOLUME /app

# Copy the Maven project definition file(s) into the container
COPY pom.xml .
COPY src/ src/

# Print the content of the current directory for debugging
RUN ls -al

# Download the dependencies and build the application
RUN mvn dependency:go-offline
RUN mvn clean package

# Use a lightweight base image with Java 17 pre-installed
FROM maven:3.9.6-eclipse-temurin-17

RUN ls -al

# Copy the application JAR file from the previous stage
COPY --from=build /target/heliosx-consultation-0.0.1-SNAPSHOT.jar .
# Command to run your application when the container starts
CMD ["java", "-jar", "heliosx-consultation-0.0.1-SNAPSHOT.jar"]
