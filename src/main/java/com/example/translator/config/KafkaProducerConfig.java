package com.example.translator.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic createTopic(){
        return new NewTopic("translations",3,(short)1 );
    }
    @Bean
    public NewTopic createTranslationCompleteTopic(){
        return new NewTopic("translationCompleted",2,(short)1);
    }
}
