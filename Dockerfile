FROM openjdk:latest

ADD target/starwars-0.0.1-SNAPSHOT starwars.jar

ENTRYPOINT ["java","-jar","starwars.jar"]

EXPOSE 8080