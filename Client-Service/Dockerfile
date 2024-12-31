FROM openjdk:17-jdk

COPY target/*.jar Client.jar

EXPOSE 8086

ENTRYPOINT ["java","-jar","/Client.jar"]