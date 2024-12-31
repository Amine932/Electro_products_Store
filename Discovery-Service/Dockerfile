FROM openjdk:17-jdk

COPY target/*.jar Discovery.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","/Discovery.jar"]