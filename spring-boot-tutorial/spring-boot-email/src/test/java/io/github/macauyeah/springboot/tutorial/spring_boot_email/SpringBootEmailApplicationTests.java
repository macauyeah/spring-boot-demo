package io.github.macauyeah.springboot.tutorial.spring_boot_email;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class SpringBootEmailApplicationTests {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromAddress;
    private static final Logger LOG = LoggerFactory.getLogger(SpringBootEmailApplicationTests.class);

    @Test
    void contextLoads() {
        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(fromAddress);
            mailMessage.setTo("wingzzz2003@hotmail.com");
            mailMessage.setText("this is backend email trigger for spring boot");
            mailMessage.setSubject("spring boot test mail");

            // Sending the mail
            javaMailSender.send(mailMessage);
            LOG.debug("Mail Sent Successfully...");
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            LOG.error("Error while Sending Mail");
            throw new RuntimeException(e);
        }
    }

}
