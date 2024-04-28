package com.example.translator.service;

import com.example.translator.api.TranslationApi;
import com.example.translator.dto.TranslationDto;
import com.example.translator.mapper.TranslationMapper;
import com.example.translator.model.TranslationResponse;
import com.example.translator.request.DataRequestForTranslation;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service

public class TranslatorService implements TranslationApi {


    private final WebClient webClient;
    private final KafkaMessageProducer kafkaMessageProducer;

    public TranslatorService(WebClient webClient, KafkaMessageProducer kafkaMessageProducer) {
        this.webClient = webClient;
        this.kafkaMessageProducer = kafkaMessageProducer;
    }


    public TranslationDto getTranslationEn(DataRequestForTranslation dataRequestForTranslation) {
        dataRequestForTranslation.setTarget_lang("EN");
        TranslationDto translation = webClient.post()
                .uri("https://api-free.deepl.com/v2/translate")
                .header("Authorization", "DeepL-Auth-Key 5faf23e8-6f6d-4fff-ad4c-6702437406f2:fx")
                .header("Content-Type", "application/json")
                .bodyValue(dataRequestForTranslation)
                .retrieve()
                .bodyToMono(TranslationResponse.class)
                .map(TranslationMapper::mapFromTranslationResponseToTranslationDto)
                .block();

        kafkaMessageProducer.sendMessageToTopic(translation);
        return translation;

    }

    @Override
    public TranslationDto getTranslationPl(DataRequestForTranslation dataRequestForTranslation) {
        dataRequestForTranslation.setTarget_lang("PL");
        TranslationDto translation = webClient.post()
                .uri("https://api-free.deepl.com/v2/translate")
                .header("Authorization", "DeepL-Auth-Key 5faf23e8-6f6d-4fff-ad4c-6702437406f2:fx")
                .header("Content-Type", "application/json")
                .bodyValue(dataRequestForTranslation)
                .retrieve()
                .bodyToMono(TranslationResponse.class)
                .map(TranslationMapper::mapFromTranslationResponseToTranslationDto)
                .block();

        kafkaMessageProducer.sendMessageToTopic(translation);
        return translation;
    }

}
