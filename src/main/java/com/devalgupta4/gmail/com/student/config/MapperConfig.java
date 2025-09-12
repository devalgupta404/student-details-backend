package com.devalgupta4.gmail.com.student.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig { @Bean
public ModelMapper modelMapper() {
    return new ModelMapper();
}

}
