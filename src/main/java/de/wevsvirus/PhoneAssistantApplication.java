package de.wevsvirus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PhoneAssistantApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PhoneAssistantApplication.class, args);
    }
}
