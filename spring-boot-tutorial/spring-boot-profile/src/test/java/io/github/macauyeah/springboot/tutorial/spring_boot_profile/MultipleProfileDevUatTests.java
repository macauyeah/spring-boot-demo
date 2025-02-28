package io.github.macauyeah.springboot.tutorial.spring_boot_profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = { "dev", "uat" })
class MultipleProfileDevUatTests {
    @Value("${application.custom.url}")
    private String url;
    @Value("${spring.profiles.active}")
    private String springProfilesActive;
    @Value("${application.custom.overwrite-by-dev}")
    private String overwriteByDev;
    @Value("${application.custom.overwrite-by-uat}")
    private String overwriteByUat;

    private static final Logger LOG = LoggerFactory.getLogger(MultipleProfileDevUatTests.class);

    @Test
    void contextLoads() {
        // in @SpringBootTest, ${spring.profiles.active} seems that control by mvn
        // compile result. it won't changed by @ActiveProfiles annotation
        // "mvn test -Pdev" will pass the test,
        // but "mvn test -Puat" or "mvn test -Pdev -Dci=true" will be fail.
        // assertEquals("dev", springProfilesActive);

        LOG.info("in MultipleProfileDevUatTests, spring.profiles.active:{}", springProfilesActive);

        // multiple profiles are applied, just like runtime argument
        // --spring.profiles.active=dev,uat
        assertEquals("uat-overwrited", overwriteByUat);
        assertEquals("dev-overwrited", overwriteByDev);
        assertEquals("uat-url", url);
    }

}
