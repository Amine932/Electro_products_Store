FROM openjdk:17-jdk

COPY target/*.jar Sale.jar
COPY src/main/resources/application.properties app.properties

EXPOSE 8083

ENTRYPOINT ["java","-jar","/Sale.jar"]
