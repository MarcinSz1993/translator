package com.example.translator.restcontroller;

import com.example.translator.dto.TranslationDto;
import com.example.translator.request.DataRequestForTranslation;
import com.example.translator.service.TranslatorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

@RestController
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping("/translator")
public class TranslationController {

    private final TranslatorService translatorService;
    @PostMapping("/translateEn")
    public TranslationDto translateEn(@RequestBody DataRequestForTranslation dataRequestForTranslation){
        return translatorService.getTranslationEn(dataRequestForTranslation);
    }
    @PostMapping("/translatePl")
    public TranslationDto translatePl(@RequestBody DataRequestForTranslation dataRequestForTranslation){
        return translatorService.getTranslationPl(dataRequestForTranslation);
    }



}
