FROM maven:3.9.7-eclipse-temurin-17 as backend-build
COPY ./spring-boot-web-api-data /opt/sourceCode/spring-boot-web-api-data
WORKDIR /opt/sourceCode/spring-boot-web-api-data
ARG mavenProfile=uat
ARG gitBuildVersion=default-docker-build
RUN --mount=type=cache,target=/root/.m2/repository/ mvn clean compile package -P ${mavenProfile} -Dgit.build.version=${gitBuildVersion} -Dmaven.test.skip=true

FROM eclipse-temurin:17.0.10_7-jdk-jammy
COPY --from=backend-build /opt/sourceCode/spring-boot-web-api-data/target/spring-boot-web-api-data-0.0.1-SNAPSHOT.war /opt/spring-boot-web-api-data.war
WORKDIR /opt/
ENTRYPOINT ["java", "-jar", "spring-boot-web-api-data.war"]

HEALTHCHECK --interval=60s --timeout=10s --retries=5 --start-period=30s \
  CMD curl --fail-with-body localhost:8080/api/version || exit 1
