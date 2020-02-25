FROM maven:3.6-jdk-8 AS build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn -f pom.xml clean package -DskipTests=true


FROM openjdk:8
EXPOSE 8761
COPY --from=build /target/easy-ride-naming-server.jar discovery.jar
ENTRYPOINT ["java","-jar","discovery.jar"]
