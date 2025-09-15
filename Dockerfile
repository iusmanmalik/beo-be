# Use OpenJDK 17 with Alpine
FROM openjdk:17-oracle

VOLUME /tmp
ARG JAR_FILE=target/dynamicforms-1.0.0.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]