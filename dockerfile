FROM maven:3-openjdk-17 as builder
ARG COMMIT
COPY src src/
COPY pom.xml .
RUN mvn --batch-mode -Drevision=${COMMIT} -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn package
RUN java -Djarmode=layertools -jar target/*.jar extract

FROM openjdk:17-jdk-alpine as runner
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
workdir app
COPY --from=builder spring-boot-loader ./
COPY --from=builder dependencies ./
COPY --from=builder snapshot-dependencies ./
# COPY static ./static/
RUN true
COPY --from=builder application ./
ARG COMMIT
ENV COMMIT=${COMMIT}
ENTRYPOINT ["java","-XX:+PrintFlagsFinal","org.springframework.boot.loader.JarLauncher"]
