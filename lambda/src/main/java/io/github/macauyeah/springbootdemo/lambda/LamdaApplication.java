package io.github.macauyeah.springbootdemo.lambda;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LamdaApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(LamdaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		ChainComparator.sortExample();
	}
}
