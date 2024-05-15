package com.example.translator.restcontroller;

import com.example.translator.model.Section;
import com.example.translator.request.AddSectionRequest;
import com.example.translator.request.AddWordToSectionRequest;
import com.example.translator.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/section")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping("/")
    public Section addSection(@AuthenticationPrincipal UserDetails userDetails, @RequestBody AddSectionRequest request) {
        return sectionService.addSection(userDetails, request);
    }

    @PutMapping("/")
    public Section addWordToSection(@RequestBody AddWordToSectionRequest request) throws Throwable {
        return sectionService.addWordToSection(request);
    }

    @GetMapping("/")
    public List<Section> showAllSections(@AuthenticationPrincipal UserDetails userDetails){
       return sectionService.getAllSections(userDetails.getUsername());
    }


}