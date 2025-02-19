# profile

## activate profile by specificing "spring.profiles.active"
```bash
mvn clean compile spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev --spring.profiles.active=uat"
mvn clean compile spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev,uat"

mvn clean compile package
java -jar target/spring-boot-profile-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --spring.profiles.active=uat
java -jar target/spring-boot-profile-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev,uat
```

if there are conflict properties, latest active profile properties seems overwrite previous profile properties. in this example, application.custom.url will be "uat-url" [application-dev.properties](src/main/resources/application-dev.properties), [application-uat.properties](src/main/resources/application-uat.properties)
```
# application-dev.properties
application.custom.url=dev-url
# application-uat.properties
application.custom.url=uat-url

# runtime output of --spring.profiles.active=dev --spring.profiles.active=uat
2025-02-19T11:37:11.550+08:00  INFO 51473 --- [spring-boot-profile] [           main] i.g.m.s.t.spring_boot_profile.AppRunner  : spring.profiles.active: dev,uat
2025-02-19T11:37:11.550+08:00  INFO 51473 --- [spring-boot-profile] [           main] i.g.m.s.t.spring_boot_profile.AppRunner  : application.custom.overwrite-by-dev:dev-overwrited
2025-02-19T11:37:11.550+08:00  INFO 51473 --- [spring-boot-profile] [           main] i.g.m.s.t.spring_boot_profile.AppRunner  : application.custom.overwrite-by-uat:uat-overwrited
2025-02-19T11:37:11.550+08:00  INFO 51473 --- [spring-boot-profile] [           main] i.g.m.s.t.spring_boot_profile.AppRunner  : application.custom.url:uat-url
```

## active profile by mvn pom.xml
run with specific maven profile, then convert to spring.profiles.active

```bash
# run maven uat profile, then spring.profiles.active is uat
mvn clean compile spring-boot:run -Puat
# run maven dev profile, then spring.profiles.active is dev
mvn clean compile spring-boot:run -Pdev
# run maven default active profile, then spring.profiles.active is dev
mvn clean compile spring-boot:run
# run maven dev profile, but trigger uat profile by ci argument, then only maven uat is active, spring.profiles.active will be uat
mvn clean compile spring-boot:run -Pdev -Dci=true

# compile maven dev profile, but trigger uat profile by ci argument, then in jar file, spring.profiles.active will be uat
mvn clean compile package -Pdev -Dci=true
java -jar target/spring-boot-profile-0.0.1-SNAPSHOT.jar
# overwrite jar's spring.profiles.active by runtime comamnd
java -jar target/spring-boot-profile-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --spring.profiles.active=uat
```

from previous test results, it seems that maven only support single profile. spring-boot:run or compile jar will only see one profile. 

if we need mulitple profile, we need to active at runtime
```bash
# maven direct run, overwrite spring.profiles.active at runtime
mvn clean compile spring-boot:run -Pdev -Dci=true -Dspring-boot.run.arguments="--spring.profiles.active=dev --spring.profiles.active=uat"

# maven package, just like before, spring-boot.run.arugments is ignored because we are not running "mvn spring-boot:run"
mvn clean compile package -Pdev -Dci=true -Dspring-boot.run.arguments="--spring.profiles.active=dev --spring.profiles.active=uat"
java -jar target/spring-boot-profile-0.0.1-SNAPSHOT.jar
java -jar target/spring-boot-profile-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --spring.profiles.active=uat
java -jar target/spring-boot-profile-0.0.1-SNAPSHOT.jar --spring.profiles.active=noexisit
```