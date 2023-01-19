FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
COPY wait-for-it.sh .
VOLUME /tmp
EXPOSE 8084
ADD target/billing-service-0.0.1-SNAPSHOT.jar /appbillingservice.jar
#CMD ["java", "-jar", "/appbillingservice.jar", "--spring.profiles.active=prod"]