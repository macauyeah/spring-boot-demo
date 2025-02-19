package io.github.macauyeah.springboot.tutorial.spring_boot_profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Value("${application.custom.url}")
    private String url;
    @Value("${spring.profiles.active}")
    private String springProfilesActive;
    @Value("${application.custom.overwrite-by-dev}")
    private String overwriteByDev;
    @Value("${application.custom.overwrite-by-uat}")
    private String overwriteByUat;
    private static final Logger LOG = LoggerFactory.getLogger(AppRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("spring.profiles.active: {}", springProfilesActive);
        LOG.info("application.custom.overwrite-by-dev:{}", overwriteByDev);
        LOG.info("application.custom.overwrite-by-uat:{}", overwriteByUat);
        LOG.info("application.custom.url:{}", url);
    }

}
