FROM maven:3.9.7-eclipse-temurin-17 as backend-test
COPY ./spring-boot-web-api-data /opt/sourceCode/spring-boot-web-api-data
# assume you have other build stage compiled result
# COPY --from=frontend-build /opt/sourceCode/spring-boot-web-api-data/src/main/resources/static /opt/sourceCode/spring-boot-web-api-data/src/main/resources/static
WORKDIR /opt/sourceCode/spring-boot-web-api-data
RUN --mount=type=cache,target=/root/.m2/repository/ mvn clean compile test
