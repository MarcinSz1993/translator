package com.example.translator.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddWordToSectionRequest {
    private String sectionName;
    private String word;
}
