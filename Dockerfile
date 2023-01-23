# Fetching latest version of Java

FROM openjdk:17

#Define where your jar file resides.
ARG JAR_FILE=target/*.jar

# Copy the jar file into our app.jar
COPY ./target/berlinspringboot-0.0.1.jar /app.jar

# Exposing port 8080
EXPOSE 8080

# Starting the application
ENTRYPOINT ["java","-jar","/app.jar"]
