package com.example.translator.api;

import com.example.translator.dto.TranslationDto;
import com.example.translator.request.DataRequestForTranslation;

public interface Translatable {

    TranslationDto translate(String sourceLanguage,String languageCode, DataRequestForTranslation dataRequestForTranslation);
}
