package io.github.macauyeah.springboot.tutorial.spring_boot_email;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootEmailApplicationTests {
	@Autowired private JavaMailSender javaMailSender;
	

	@Test
	void contextLoads() {
		// Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom("macauyeahwebmail@gmail.com");
            mailMessage.setTo("wingzzz2003@hotmail.com");
            mailMessage.setText("this is backend email trigger for spring boot");
            mailMessage.setSubject("spring boot test mail");

            // Sending the mail
            javaMailSender.send(mailMessage);
            // return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            // return "Error while Sending Mail";
        }
	}

}
