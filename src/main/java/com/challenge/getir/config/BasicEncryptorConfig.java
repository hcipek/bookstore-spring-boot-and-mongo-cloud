package com.challenge.getir.config;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicEncryptorConfig {

    @Bean
    public BasicPasswordEncryptor encryptor() {
        return new BasicPasswordEncryptor();
    }
}
