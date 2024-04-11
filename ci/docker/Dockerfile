FROM gradle:jdk11-openj9 as backend-compiler
LABEL mantainer="patedwins@gmail.com"
WORKDIR /app
COPY . .

ARG MAVEN_USER_ARG
ARG MAVEN_PASSWORD_ARG

ENV MAVEN_USER=$MAVEN_USER_ARG
ENV MAVEN_PASSWORD=$MAVEN_PASSWORD_ARG

RUN gradle build --refresh-dependencies || return 0

FROM openjdk:8-alpine

ARG ARG_VERSION
ENV VERSION=$ARG_VERSION

RUN apk add tzdata
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=backend-compiler /app/pichincha-service/build/libs/pichincha-service-$VERSION.jar /app/opt/service.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/app/opt/service.jar"]
