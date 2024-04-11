FROM openjdk:11-jre-slim

WORKDIR /app

COPY pichincha-1.0.0-SNAPSHOT.jar pichincha-1.0.0.jar

CMD ["java", "-Xmx200m", "-jar", "pichincha-1.0.0.jar"]

EXPOSE 8087
