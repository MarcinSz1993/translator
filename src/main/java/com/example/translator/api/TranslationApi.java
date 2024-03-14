package com.example.translator.api;

import com.example.translator.dto.TranslationDto;
import com.example.translator.request.DataRequestForTranslation;

public interface TranslationApi {

    TranslationDto getTranslationEn(DataRequestForTranslation dataRequestForTranslation);
    TranslationDto getTranslationPl(DataRequestForTranslation dataRequestForTranslation);
}
