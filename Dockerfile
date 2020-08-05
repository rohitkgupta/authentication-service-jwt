FROM openjdk:8-jre-alpine

COPY build/libs/authentication-service-0.0.1-SNAPSHOT.jar /opt/authentication-service/

WORKDIR /opt/

RUN mkdir appConfig

WORKDIR /opt/authentication-service/

RUN mkdir logs


ENTRYPOINT java  -jar ./authentication-service-0.0.1-SNAPSHOT.jar

EXPOSE 8080