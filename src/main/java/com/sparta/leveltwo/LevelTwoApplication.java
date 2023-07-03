package com.sparta.leveltwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LevelTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LevelTwoApplication.class, args);
    }

}
