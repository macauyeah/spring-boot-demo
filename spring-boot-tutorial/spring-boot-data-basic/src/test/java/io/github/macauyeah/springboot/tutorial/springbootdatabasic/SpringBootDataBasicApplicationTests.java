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
		appleRepo.findFirstByWeight(0.0); // test oracle capability mode, will fail on spring 2.7.x, work on 3.4.x
	}

	@AfterEach
	void tearDown() {
		appleRepo.deleteAll();
	}

}
