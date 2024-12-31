FROM openjdk:17-jdk
COPY target/*.jar notification.jar
COPY src/main/resources/application.properties app.properties

EXPOSE 8040

ENTRYPOINT ["java", "-jar", "notification.jar"]
