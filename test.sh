#!/bin/bash
set -euxo pipefail

# declare -a arr=("jpaspecification" "lambda")
declare -a arr=("lambda")

## now loop through the above array
for i in "${arr[@]}"
do
   cd "$i"
   mvn clean compile test
   cd ..
done

cd "spring-boot-tutorial"
declare -a arr2=("commandline"
   "deletion"
   # "spring-boot-data-advance"
   "spring-boot-data-basic"
   "spring-boot-data-test"
   "spring-boot-profile"
   "spring-boot-rest-template-test"
   "spring-boot-web-api-basic"
   "spring-boot-web-api-data"
   "spring-boot-web-api-doc"
   "spring-boot-web-api-validate"
)
for i in "${arr2[@]}"
do
   cd "$i"
   mvn clean compile test
   cd ..
done