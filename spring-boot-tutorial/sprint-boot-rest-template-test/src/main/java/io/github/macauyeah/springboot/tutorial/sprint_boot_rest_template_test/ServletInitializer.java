package io.github.macauyeah.springboot.tutorial.sprint_boot_rest_template_test;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SprintBootRestTemplateTestApplication.class);
	}

}
