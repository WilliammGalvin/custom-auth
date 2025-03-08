package me.williamgalvin.customauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CustomAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomAuthApplication.class, args);
    }

}
