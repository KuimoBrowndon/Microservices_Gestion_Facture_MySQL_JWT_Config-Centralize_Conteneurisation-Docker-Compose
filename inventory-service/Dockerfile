FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
COPY wait-for-it.sh .
EXPOSE 8083
VOLUME /tmp
ADD target/inventory-service-0.0.1-SNAPSHOT.jar /appinventoryservice.jar
#CMD ["java", "-jar", "/appinventoryservice.jar", "--spring.profiles.active=prod"]