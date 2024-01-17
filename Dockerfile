FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app

COPY project/mvnw .
COPY project/.mvn .mvn
COPY project/pom.xml .
COPY project/src src

RUN mvn clean package -Dmaven.test.skip

FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY --from=builder /app/target/project-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]