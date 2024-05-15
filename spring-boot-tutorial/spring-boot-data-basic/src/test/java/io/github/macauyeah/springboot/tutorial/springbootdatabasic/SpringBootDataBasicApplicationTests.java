package io.github.macauyeah.springboot.tutorial.springbootdatabasic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootDataBasicApplicationTests {
	@Autowired
	AppleRepo appleRepo;

	@Test
	void contextLoads() {
		assertTrue(appleRepo.count() > 0);
	}

}
