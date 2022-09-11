#FROM openjdk:18-jdk-alpine3.9
FROM amazoncorretto:18-alpine-jdk

WORKDIR /
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
#COPY target ./target

#COPY ./target/ATM_ErfanAdine_project1-0.0.1-SNAPSHOT.jar ./targer
COPY src ./src
COPY . .
EXPOSE 8181
CMD ["./mvnw", "spring-boot:run"]