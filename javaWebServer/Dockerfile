# ==== build and compile ====
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /build
COPY . .
RUN mvn clean package

# ==== run stage ====
FROM openjdk:17-jdk
WORKDIR /app
COPY --from=builder /build/target/javawebserver-1.0-SNAPSHOT.jar app.jar
COPY config.yml config.yml
CMD ["java", "-jar", "app.jar", "server", "config.yml"]