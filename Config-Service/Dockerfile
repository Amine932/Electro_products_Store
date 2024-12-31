FROM openjdk:17-jdk

COPY target/*.jar Config.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","/Config.jar"]