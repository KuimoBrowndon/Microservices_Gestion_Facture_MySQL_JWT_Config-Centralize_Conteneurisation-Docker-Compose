FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
COPY wait-for-it.sh .
EXPOSE 8086
VOLUME /tmp
ADD target/config-service-0.0.1-SNAPSHOT.jar /appconfigservice.jar
#CMD ["java", "-jar", "/appconfigservice.jar", "--spring.profiles.active=prod"]