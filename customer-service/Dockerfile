FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
COPY wait-for-it.sh .
EXPOSE 8082
VOLUME /tmp
ADD target/customer-service-0.0.1-SNAPSHOT.jar /appcustomerservice.jar
#CMD ["java", "-jar", "/appcustomerservice.jar", "--spring.profiles.active=prod"]