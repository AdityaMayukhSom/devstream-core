FROM gradle:8-jdk21-alpine as cache
ENV GRADLE_USER_HOME /home/gradle/cache_home
ENV APP_HOME=/usr/app/
RUN mkdir -p ${GRADLE_USER_HOME}
COPY build.gradle ${APP_HOME}
WORKDIR ${APP_HOME}
RUN gradle clean build -i || return 0

FROM eclipse-temurin:21-jdk-alpine AS build-step
ENV APP_HOME=/usr/app/
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
WORKDIR $APP_HOME
COPY . .
RUN chmod +x ./gradlew 
RUN ./gradlew bootJar
# COPY build.gradle settings.gradle gradlew ${APP_HOME}
# COPY gradle/ ${APP_HOME}/gradle/
# RUN chmod +x ./gradlew && ./gradlew dependencies --refresh-dependencies

FROM eclipse-temurin:21-jre-alpine AS prod-step
ARG USER=nonrootuser
COPY --from=build-step /usr/app/build/libs/*.jar app.jar
RUN adduser -D $USER
ENV SPRING_PROFILES_ACTIVE production
USER $USER
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]