# Image to compile
FROM gradle:6.3-jdk14 AS build-env
WORKDIR /app

# Copy source and generate the jar file
COPY . ./
RUN gradle build --no-daemon

# Build runtime image
FROM openjdk:14-jdk-alpine
EXPOSE 8082

# Copy the jar and get it ready for execution
COPY --from=build-env /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]