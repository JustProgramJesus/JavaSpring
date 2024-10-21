package com.example.contacts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.contacts")
public class AppConfig {

    @Bean
    public ContactService contactService() {
        return new ContactService();
    }
}
