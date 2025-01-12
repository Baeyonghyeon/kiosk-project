package com.shoushoubackenddeveloper.kiosk_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KioskProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(KioskProjectApplication.class, args);
    }

}
