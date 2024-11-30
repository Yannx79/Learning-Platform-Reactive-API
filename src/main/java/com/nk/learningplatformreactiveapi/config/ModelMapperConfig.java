package com.nk.learningplatformreactiveapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean("userModelMapper")
    public ModelMapper userModelMapper() {
        return new ModelMapper();
    }

    @Bean("courseModelMapper")
    public ModelMapper courseModelMapper() {
        return new ModelMapper();
    }

    @Bean("quizModelMapper")
    public ModelMapper quizModelMapper() {
        return new ModelMapper();
    }

    @Bean("lessonModelMapper")
    public ModelMapper lessonModelMapper() {
        return new ModelMapper();
    }

    @Bean("enrollmentModelMapper")
    public ModelMapper enrollmentModelMapper() {
        return new ModelMapper();
    }

}
