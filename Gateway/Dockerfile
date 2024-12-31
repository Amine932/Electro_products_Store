FROM openjdk:17-jdk

COPY target/*.jar Gateway.jar

EXPOSE 8088

ENTRYPOINT ["java","-jar","/Gateway.jar"]