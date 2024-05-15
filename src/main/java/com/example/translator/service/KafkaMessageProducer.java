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

    private final KafkaTemplate<String, String> kafkaTemplate;

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

    public void sendMessageTranslationCompletedToTopic(String message){
        try {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("translationCompleted", message);
            future.whenComplete((result,ex) -> {
                if(ex == null) {
                    System.out.println("Sent message: Translation completed!");
                } else {
                    System.out.println("Something went wrong");
                }
            });
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
