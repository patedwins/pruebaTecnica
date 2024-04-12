FROM openjdk:11-jre-slim

WORKDIR /app

ARG USER_ARG
ARG PASSWORD_ARG
ARG URL_ARG

ENV USER_ENV=$USER_ARG
ENV PASSWORD_ENV=$PASSWORD_ARG
ENV URL_ENV=$URL_ARG

COPY /pichincha-service/build/libs/pichincha-service-1.0.0-SNAPSHOT.jar pichincha-1.0.0.jar

CMD ["java", "-Xmx200m", "-jar", "pichincha-1.0.0.jar"]

EXPOSE 8087
