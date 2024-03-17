package com.example.translator.controller;

import com.example.translator.dto.TranslationDto;
import com.example.translator.model.UserModel;
import com.example.translator.repository.UserRepository;
import com.example.translator.request.DataRequestForTranslation;
import com.example.translator.service.JwtService;
import com.example.translator.service.TranslatorService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String homePage(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("authToken".equals(cookie.getName())){
                    String token = cookie.getValue();

                    String username = jwtService.extractUsername(token);
                    UserModel user = userRepository.findByUsername(username).orElseThrow();

                    if(jwtService.isValid(token,user)){
                        return "translator_view";
                    } else {
                        return "translator_view_error";
                    }
                }
            }
        }
        return "translator_view_error";
    }
    @PostMapping("/translateEn")
    public String translateFromPlToEn(@ModelAttribute DataRequestForTranslation dataRequestForTranslation, Model model, HttpSession session) {
        TranslationDto translationDto = translatorService.getTranslationEn(dataRequestForTranslation);
        String translation = translationDto.getTranslation();
        model.addAttribute("translation", translation);
        session.setAttribute("lastTranslation",translation);
        session.setAttribute("wordToTranslate",dataRequestForTranslation.getText()[0]);
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