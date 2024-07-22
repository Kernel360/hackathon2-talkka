FROM amazoncorretto:17

LABEL authors="Sungjun Park"

ARG JAR_FILE=build/libs/*.jar

WORKDIR /app

COPY ${JAR_FILE} /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
