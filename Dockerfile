FROM openjdk:17-alpine

LABEL authors="Szymon"

WORKDIR /app

COPY target/ZeroOneBackend.jar /app/ZeroOneBackend.jar

ENTRYPOINT ["java", "-jar", "ZeroOneBackend.jar"]