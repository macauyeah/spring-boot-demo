FROM maven:3.9.7-eclipse-temurin-17 as backend-compile
COPY ./spring-boot-web-api-data /opt/sourceCode/spring-boot-web-api-data
WORKDIR /opt/sourceCode/spring-boot-web-api-data
RUN --mount=type=cache,target=/root/.m2/repository/ mvn clean compile

FROM maven:3.9.7-eclipse-temurin-17 as backend-test
COPY --from=backend-compile /opt/sourceCode/spring-boot-web-api-data /opt/sourceCode/spring-boot-web-api-data
WORKDIR /opt/sourceCode/spring-boot-web-api-data
RUN --mount=type=cache,target=/root/.m2/repository/ mvn test
# rerun test case anytime
CMD ["mvn", "test"]


# >docker image build -t test -f test.Dockerfile

# >podman image build -t test -f test.Dockerfile
# >podman volume create m2cache
# >podman container run -v m2cache:/root/.m2/repository/ --rm test
# >podman builder prune -af