# openapi generator
```bash
openapi-generator-cli generate \
  -i http://localhost:8080/v3/api-docs \
  --api-package io.github.macauyeah.springboot.tutorial.openapiclient.api \
  --model-package io.github.macauyeah.springboot.tutorial.openapiclient.model \
  --invoker-package io.github.macauyeah.springboot.tutorial.openapiclient.invoker \
  --group-id io.github.macauyeah.springboot.tutorial \
  --artifact-id spring-boot-web-api-open-api-client \
  --artifact-version 0.0.1-SNAPSHOT \
  -g java \
  -p useJakartaEe=true \
  -p useSpringBoot3=true \
  --library webclient \
  -o spring-boot-web-api-open-api-client
```