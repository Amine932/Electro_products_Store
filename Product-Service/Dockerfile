FROM openjdk:17-jdk

COPY target/*.jar Product.jar

EXPOSE 8087

ENTRYPOINT ["java","-jar","/Product.jar"]