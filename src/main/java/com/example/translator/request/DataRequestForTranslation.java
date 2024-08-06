package com.example.translator.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DataRequestForTranslation {
    private String[] text;
    private String target_lang;
    private String source_lang;

    public DataRequestForTranslation(String[] text, String target_lang, String source_lang) {
        this.text = text;
        this.target_lang = target_lang;
        this.source_lang = source_lang;
    }
}
