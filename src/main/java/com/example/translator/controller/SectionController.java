package com.example.translator.controller;

import com.example.translator.model.Section;
import com.example.translator.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/section")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping("/")
    public Section addSection(@AuthenticationPrincipal UserDetails userDetails, @RequestBody String sectionName) {
        return sectionService.addSection(userDetails, sectionName);
    }
}