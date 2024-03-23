package com.example.translator.mapper;

import com.example.translator.dto.TranslationDto;
import com.example.translator.model.TranslationFromApi;
import com.example.translator.model.TranslationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TranslationMapperTest {


    @Test
    public void shouldMapTranslationResponseToTranslationDto(){
        TranslationFromApi translationFromApi = new TranslationFromApi("EN","Translation1");
        TranslationResponse translationResponse = new TranslationResponse(new TranslationFromApi[]{translationFromApi});

        TranslationDto expectedValue = new TranslationDto("Translation1");
        TranslationDto actualValue = TranslationMapper.mapFromTranslationResponseToTranslationDto(translationResponse);

        Assertions.assertEquals(expectedValue,actualValue);

    }

    @Test
    public void shouldThrowNullPointerExceptionWhenTranslationResponseIsNull(){

        TranslationResponse translationResponse = new TranslationResponse(null);
        Assertions.assertThrows(NullPointerException.class, () -> TranslationMapper.mapFromTranslationResponseToTranslationDto(translationResponse));






    }
}
