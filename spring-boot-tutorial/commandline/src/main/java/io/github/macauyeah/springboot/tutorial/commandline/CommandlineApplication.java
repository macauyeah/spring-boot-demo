package io.github.macauyeah.springboot.tutorial.commandline;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandlineApplication implements ApplicationRunner {
	static Logger LOG = LoggerFactory.getLogger(CommandlineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CommandlineApplication.class, args);
	}

	// mvn clean compile spring-boot:run -Dspring-boot.run.arguments="--JarCommandOptions=test --JarCommandOptions=test2 --SomeCommandOptions=some --SomeCommandOptions=some2"
	// java -jar target/commandline-0.0.1-SNAPSHOT.jar --JarCommandOptions=test --JarCommandOptions=test2 --SomeCommandOptions=some --SomeCommandOptions=some2
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Set<String> options = args.getOptionNames();
		options.stream().forEach(option -> {
			LOG.debug("option name:" + option);

			args.getOptionValues(option).forEach(optionValue -> {
				LOG.debug("option values:" + optionValue);
			});
		});
		LOG.debug("program end.");
	}

}
