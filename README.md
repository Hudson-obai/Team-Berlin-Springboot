# Team-Berlin-Springboot
Team Berlin Backend Written in Java Springboot For The SpaceYaTech Project
# database
This project has made use of postgresql database

db name : berlin

password: berlin

username: berlin

NB: db credentials can be set to any of your choice provided they are declared in the application.yml file

# Liquibase v 4.7.1

For db changelog tracking and versioning

# Swagger open api documentation

http://{YOUR DOMAIN eg. localhost:8080}/swagger-ui/index.html

# Start application in docker

mvn clean install -DskipTests=true / mvn package spring-boot:repackage -DskipTests=true

run 'docker compose up --build'
