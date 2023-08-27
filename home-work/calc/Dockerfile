# Use OpenJDK as base image
FROM openjdk:17

# Create a new app directory for my application files.
RUN mkdir -p /app

# Copy the app files from host machine to image.
COPY target/calc-1.0-SNAPSHOT.jar /app

# Set the default directory for executing future commands.
WORKDIR /app

# Run the App class
CMD [ "java", "-jar", "calc-1.0-SNAPSHOT.jar"]