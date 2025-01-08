#Maven stage
FROM maven:3.8.8-eclipse-temurin-21 AS maven-builder
WORKDIR /application
COPY pom.xml .
COPY checkstyle.xml .
COPY src ./src
RUN mvn package -DskipTests
RUN ls -l /application/target

#Builder stage
FROM openjdk:21-jdk-slim AS builder
WORKDIR application
ARG JAR_FILE=target/football-manager-0.0.1-SNAPSHOT.jar
COPY --from=maven-builder /application/${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

#Final stage
FROM openjdk:21-jdk-slim
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
EXPOSE 8080
