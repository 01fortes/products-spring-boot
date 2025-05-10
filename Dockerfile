FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew dependencies

COPY src ./src

RUN ./gradlew bootJar

FROM eclipse-temurin:21-jre

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx256m", "-jar", "/app.jar"]