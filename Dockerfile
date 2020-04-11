FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sbk.jar
ENTRYPOINT ["java","-jar","/sbk.jar"]
