package com.example.translator.controller;

import com.example.translator.model.Section;
import com.example.translator.request.AddSectionRequest;
import com.example.translator.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sections")
@RequiredArgsConstructor
public class SectionViewController {

    private final SectionService sectionService;

    @GetMapping("/create")
    public String getCreateSectionPage(Model model){
        model.addAttribute("newSection",new Section());
        return "create_section_page";
    }

    @PostMapping("/createSection")
    public String createSection(@ModelAttribute AddSectionRequest request,
                                @AuthenticationPrincipal UserDetails userDetails)
                                {
            if(userDetails == null){
            return "redirect:/loginPage";
        }

        sectionService.addSection(userDetails,request);
        return "redirect:/";
        }


}
