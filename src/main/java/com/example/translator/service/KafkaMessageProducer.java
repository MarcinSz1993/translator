package com.example.translator.service;

import com.example.translator.dto.TranslationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class KafkaMessageProducer {

    private final KafkaTemplate<String, TranslationDto> template;

    public void sendMessageToTopic(TranslationDto translation){
        try {
            CompletableFuture<SendResult<String, TranslationDto>> future = template.send("translations", translation);
            future.whenComplete((result, ex) -> {
                if(ex == null) {
                    System.out.println("Sent message: " + translation.toString() +
                            "with offset: " + result.getRecordMetadata().offset());

                }else {
                    System.out.println("Unable to send message: " + translation.toString());
                }

            });
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage() );
        }
    }
}
