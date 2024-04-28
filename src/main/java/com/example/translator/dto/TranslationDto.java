package com.example.translator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationDto {

    private String translation;

    @Override
    public String toString() {
        return "TranslationDto{" +
                "translation='" + translation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TranslationDto that = (TranslationDto) o;

        return getTranslation().equals(that.getTranslation());
    }

    @Override
    public int hashCode() {
        return getTranslation().hashCode();
    }
}
