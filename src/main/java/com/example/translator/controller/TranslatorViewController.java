package com.example.translator.controller;

import com.example.translator.dto.TranslationDto;
import com.example.translator.request.DataRequestForTranslation;
import com.example.translator.service.TranslatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TranslatorViewController {

    private final TranslatorService translatorService;

    @GetMapping("/")
    public String homePage(){
        return "translator_view";
    }

    @PostMapping("/translateEn")
    public String translateFromPlToEn(@ModelAttribute DataRequestForTranslation dataRequestForTranslation, Model model) {
        TranslationDto translationDto = translatorService.getTranslationEn(dataRequestForTranslation);
        String translation = translationDto.getTranslation();
        model.addAttribute("translation", translation);
        return "translation_result_view";
    }

    @PostMapping("/translatePl")
    public String translateFromEnToPl(@ModelAttribute DataRequestForTranslation dataRequestForTranslation, Model model) {
        TranslationDto translationDto = translatorService.getTranslationPl(dataRequestForTranslation);
        String translation = translationDto.getTranslation();
        model.addAttribute("translation", translation);
        return "translation_result_view";
    }
}