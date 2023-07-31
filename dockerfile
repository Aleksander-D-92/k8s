FROM openjdk:17-alpine as builder
COPY .. .
RUN apk add maven
RUN mvn clean install
FROM openjdk:17-alpine
COPY --from=builder /target/backend.jar /target/backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/target/backend.jar"]
