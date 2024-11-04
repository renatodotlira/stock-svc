FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=deploy

ENTRYPOINT ["java", "-jar", "app.jar"]
