package io.github.macauyeah.springboot.tutorial.springbootdatatest;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootDataTestApplicationTests {
	@Autowired
	AppleRepo appleRepo;

	@Test
	void contextLoads() {
		Apple apple = new Apple();
		apple.setUuid(UUID.randomUUID().toString());
		apple.setWeight(100.0);
		apple.setGravity(1000.0);
		appleRepo.save(apple);
	}

}
