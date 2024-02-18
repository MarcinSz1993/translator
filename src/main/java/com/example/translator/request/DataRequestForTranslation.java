package com.example.translator.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DataRequestForTranslation {
    private String[] text;

    private String target_lang;

    public DataRequestForTranslation(String[] text, String target_lang) {
        this.text = text;
        this.target_lang = target_lang;
    }
}
