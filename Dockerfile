FROM eclipse-temurin:17

WORKDIR /app

COPY build/libs/backend-1.0.jar /app/totaleasy-backend.jar

CMD ["java", "-jar", "totaleasy-backend.jar"]
