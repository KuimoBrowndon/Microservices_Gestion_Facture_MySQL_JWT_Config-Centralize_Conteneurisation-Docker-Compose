FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
COPY wait-for-it.sh .
EXPOSE 8761
VOLUME /tmp
ADD target/eureka-service-0.0.1-SNAPSHOT.jar /appeurekaservice.jar
#CMD ["java", "-jar", "/appeurekaservice.jar", "--spring.profiles.active=prod"]