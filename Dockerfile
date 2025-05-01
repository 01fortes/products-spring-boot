# 1. Используем официальный образ JDK для сборки
FROM eclipse-temurin:21-jdk AS builder

# 2. Указываем рабочую директорию внутри контейнера
WORKDIR /app

# 3. Копируем gradle файлы и проект
COPY build.gradle.kts settings.gradle.kts gradlew /app/
COPY gradle /app/gradle
COPY src /app/src

# 4. Ставим разрешение на gradlew (если нужно)
RUN chmod +x ./gradlew

# 5. Сборка jar файла
RUN ./gradlew bootJar --no-daemon

# 6. Используем более лёгкий рантайм-образ для запуска
FROM eclipse-temurin:21-jre

# 7. Копируем jar из предыдущего контейнера
COPY --from=builder /app/build/libs/*.jar app.jar

# 8. Указываем порт (если хочешь, опционально)
EXPOSE 8080

# 9. Стартовое приложение
ENTRYPOINT ["java", "-Xmx256m", "-jar", "/app.jar"]