package io.github.macauyeah.springboot.tutorial.springbootdatabasic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootDataBasicApplicationTests {
	@Autowired
	AppleRepo appleRepo;

	@Test
	void contextLoads() {
		assertEquals(2, appleRepo.count());
	}

	@AfterEach
	void tearDown() {
		appleRepo.deleteAll();
	}

}
