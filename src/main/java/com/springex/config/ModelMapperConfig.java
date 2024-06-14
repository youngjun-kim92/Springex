package com.springex.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean// 다른 service나 controller등에서 주입하여 사용할수 있다.
    public ModelMapper getMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)// 필드이름일치하는 경우 자동매핑
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)// 접근레벨은 private
                .setMatchingStrategy(MatchingStrategies.STRICT);// 이름이 정확하게 일치해야 함.

        return modelMapper;
    }
}