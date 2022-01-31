FROM maven:3-openjdk-17 as builder
COPY src src/
COPY pom.xml .
RUN mvn package

FROM openjdk:17-jdk-alpine as runner

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=builder /target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]