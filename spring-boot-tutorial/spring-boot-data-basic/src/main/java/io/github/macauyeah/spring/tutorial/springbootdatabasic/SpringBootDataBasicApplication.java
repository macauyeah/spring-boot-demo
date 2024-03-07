package io.github.macauyeah.spring.tutorial.springbootdatabasic;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringBootDataBasicApplication implements ApplicationRunner {
	@Autowired
	private AppleRepo appleRepo;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataBasicApplication.class, args);
	}
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


	private void dump(){
		jdbcTemplate.execute("SCRIPT TO 'dump.sql'");
	}
}
