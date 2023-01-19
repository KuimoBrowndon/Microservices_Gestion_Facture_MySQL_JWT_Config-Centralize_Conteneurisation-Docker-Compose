FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
COPY wait-for-it.sh .
VOLUME /tmp
EXPOSE 8081
ADD target/gateway-service-0.0.1-SNAPSHOT.jar /appgatewayservice.jar
#CMD ["java", "-jar", "/appgatewayservice.jar", "--spring.profiles.active=prod"]