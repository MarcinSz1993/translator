package com.example.translator.mapper;

import com.example.translator.dto.TranslationDto;
import com.example.translator.model.TranslationFromApi;
import com.example.translator.model.TranslationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TranslationMapper {

    public static TranslationDto mapFromTranslationResponseToTranslationDto(TranslationResponse translationResponse){
        TranslationFromApi[] translations = translationResponse.getTranslations();
        List<TranslationFromApi> list = Arrays.asList(translations);
        String text = list.get(0).getText();

        return new TranslationDto(text);
    }





}
