package com.github.CodeNekomancer.OADA_Backend.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConf {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
