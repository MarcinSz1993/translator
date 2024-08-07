package com.example.translator.service;

import com.example.translator.api.Translatable;
import com.example.translator.config.ApiConfig;
import com.example.translator.dto.TranslationDto;
import com.example.translator.mapper.TranslationMapper;
import com.example.translator.model.TranslationResponse;
import com.example.translator.request.DataRequestForTranslation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TranslatorService implements Translatable {
    private final WebClient webClient;
    private final ApiConfig apiConfig;

    public TranslatorService(WebClient webClient, ApiConfig apiConfig) {
        this.webClient = webClient;
        this.apiConfig = apiConfig;
    }
    public TranslationDto translate(String sourceLanguage,
                                    String targetLanguage,
                                    DataRequestForTranslation dataRequestForTranslation) {
        switch (targetLanguage) {
            case "EN", "PL", "DE","FR" ->
                dataRequestForTranslation.setTarget_lang(targetLanguage);}
        switch (sourceLanguage) {
            case "EN", "PL", "DE", "FR" ->
                dataRequestForTranslation.setSource_lang(sourceLanguage);
        }
        return translation(dataRequestForTranslation);
    }

    private TranslationDto translation(DataRequestForTranslation dataRequestForTranslation) {
        return webClient.post()
                .uri(apiConfig.getUri())
                .header(HttpHeaders.AUTHORIZATION, apiConfig.getApiKey())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(dataRequestForTranslation)
                .retrieve()
                .bodyToMono(TranslationResponse.class)
                .map(TranslationMapper::mapFromTranslationResponseToTranslationDto)
                .block();
    }
}
