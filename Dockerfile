FROM openjdk:17-jdk-slim
ARG JAR-FILE=target/*.jar
COPY target/pagamentos-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/app.jar"]