package com.example.translator.controller;

import com.example.translator.dto.TranslationDto;
import com.example.translator.service.KafkaMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaMessageProducer kafkaMessageProducer;

    @PostMapping("/send")
    public void sendMessageToTopic(@RequestBody TranslationDto translation){
        kafkaMessageProducer.sendMessageToTopic(translation);
    }
}
