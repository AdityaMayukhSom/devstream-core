FROM gradle:8.12.0-jdk21-alpine AS cache
ENV GRADLE_USER_HOME /home/gradle/cache_home
ENV APP_HOME /usr/app/
RUN mkdir -p ${GRADLE_USER_HOME}
COPY build.gradle ${APP_HOME}
WORKDIR ${APP_HOME}
RUN gradle clean build -i || return 0

FROM gradle:8.12.0-jdk21-alpine AS build-step
ARG GITHUB_REPO_USERNAME
ARG GITHUB_REPO_PASSWORD 
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
WORKDIR /usr/app/
COPY . .
RUN gradle bootJar

FROM eclipse-temurin:21-jre-alpine AS prod-step
ARG USER=nonrootuser
COPY --from=build-step /usr/app/build/libs/*.jar app.jar
RUN adduser -D $USER
ENV SPRING_PROFILES_ACTIVE production
USER $USER
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]