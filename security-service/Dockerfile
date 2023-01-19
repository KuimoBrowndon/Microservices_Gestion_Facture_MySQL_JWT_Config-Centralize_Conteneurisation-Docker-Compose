FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
COPY wait-for-it.sh .
EXPOSE 8085
VOLUME /tmp
ADD target/security-service-0.0.1-SNAPSHOT.jar /appsecurityservice.jar
#CMD ["java", "-jar", "/appsecurityservice.jar", "--spring.profiles.active=prod"]