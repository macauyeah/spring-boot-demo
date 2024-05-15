package io.github.macauyeah.springboot.tutorial.springbootdatabasic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("skip")
class SpringBootDataBasicSkipRunnerTests {
	@Autowired
	AppleRepo appleRepo;

	@Test
	void contextLoads() {
		assertTrue(appleRepo.count() == 0);
	}

}
