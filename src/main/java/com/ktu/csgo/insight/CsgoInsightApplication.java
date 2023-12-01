package com.ktu.csgo.insight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication()
public class CsgoInsightApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsgoInsightApplication.class, args);
    }

}
