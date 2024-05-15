package io.github.macauyeah.springboot.tutorial.springbootdatabasic;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("!skip")
public class BasicApplicationRunner implements ApplicationRunner {
	@Autowired
	private AppleRepo appleRepo;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Apple apple = new Apple();
		apple.setUuid(UUID.randomUUID().toString());
		apple.setWeight(100.0);
		apple.setGravity(1000.0);
		appleRepo.save(apple);

		Apple apple2 = new Apple();
		apple2.setUuid(UUID.randomUUID().toString());
		apple2.setGravity(1000.0);
		appleRepo.save(apple2);

		dump();
	}

	private void dump() {
		jdbcTemplate.execute("SCRIPT TO 'dump.sql'");
	}
}
