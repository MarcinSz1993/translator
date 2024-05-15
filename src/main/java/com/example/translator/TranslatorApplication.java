package com.example.translator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableCaching
public class TranslatorApplication {
    @Bean
    public WebClient webClient(){
       return WebClient.create();
    }

    public static void main(String[] args) {
        SpringApplication.run(TranslatorApplication.class, args);
    }

}
