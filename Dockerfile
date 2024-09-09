FROM eclipse-temurin:21-jdk-alpine AS build-step
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew ${APP_HOME}
COPY gradle/ ${APP_HOME}/gradle/
RUN chmod +x ./gradlew && ./gradlew dependencies --refresh-dependencies
COPY . .
RUN chmod +x ./gradlew && ./gradlew bootJar


FROM eclipse-temurin:21-jre-alpine AS prod-step
COPY --from=build-step /usr/app/build/libs/*.jar app.jar
RUN groupadd -r nonroot && useradd -r -g nonroot nonroot
ENV SPRING_PROFILES_ACTIVE production
USER nonroot
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]