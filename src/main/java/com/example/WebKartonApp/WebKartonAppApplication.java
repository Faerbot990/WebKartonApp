package com.example.WebKartonApp;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WebKartonAppApplication {

    public static void main(String[] args) {
        log.info("Starting app");
        SpringApplication.run(WebKartonAppApplication.class, args);
        log.info("app started");
    }
}
