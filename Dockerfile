FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/item-service-0.0.1-SNAPSHOT.jar ./

EXPOSE 9004

ENTRYPOINT ["java","-jar", "/app/item-service-0.0.1-SNAPSHOT.jar"]