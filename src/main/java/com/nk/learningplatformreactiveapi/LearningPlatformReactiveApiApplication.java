package com.nk.learningplatformreactiveapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
// @ComponentScan("com.nk.learningplatformreactiveapi.controllers")
public class LearningPlatformReactiveApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningPlatformReactiveApiApplication.class, args);
    }

}
