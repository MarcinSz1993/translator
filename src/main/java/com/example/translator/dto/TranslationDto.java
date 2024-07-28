package com.example.translator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TranslationDto {

    private String translation;
    @Override
    public String toString() {
        return "TranslationDto{" +
                "translation='" + translation + '\'' +
                '}';
    }
}
