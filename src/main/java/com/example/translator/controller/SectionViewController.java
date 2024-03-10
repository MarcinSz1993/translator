package com.example.translator.controller;

import com.example.translator.model.Section;
import com.example.translator.model.UserModel;
import com.example.translator.repository.UserRepository;
import com.example.translator.request.AddSectionRequest;
import com.example.translator.request.AddWordToSectionRequest;
import com.example.translator.service.JwtService;
import com.example.translator.service.SectionService;
import com.example.translator.service.UserModelService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sections")
@RequiredArgsConstructor
public class SectionViewController {

    private final SectionService sectionService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserModelService userModelService;

    @GetMapping("/create")
    public String getCreateSectionPage(Model model){
        model.addAttribute("newSection",new Section());
        return "redirect:/sections/all";
    }

    @PostMapping("/createSection")
    public String createSection(@ModelAttribute AddSectionRequest request,
                                HttpServletRequest servletRequest)
                                {
                                    Cookie[] cookies = servletRequest.getCookies();
                                    for(Cookie cookie : cookies){
                                        if("authToken".equals(cookie.getName())) {
                                            String token = cookie.getValue();
                                            String username = jwtService.extractUsername(token);
                                            UserModel user = userRepository.findByUsername(username).orElseThrow();
                                            sectionService.addSection(user, request);

                                        }
                                    }
                                    return "redirect:/sections/all";
        }
        @GetMapping("/all")
        public String showSectionList(Model model, HttpSession session){
            String username =(String) session.getAttribute("username");
            List<Section> allSections = sectionService.getAllSections(username);
            String lastTranslation = (String) session.getAttribute("lastTranslation");
            String wordToTranslate = (String) session.getAttribute("wordToTranslate");

            AddWordToSectionRequest addWordForm = new AddWordToSectionRequest();
            model.addAttribute("addWordForm",addWordForm);
            model.addAttribute("sections",allSections);
            model.addAttribute("lastTranslation",lastTranslation);
            model.addAttribute("wordToTranslate",wordToTranslate);

            return "sections_view";
        }

        @PostMapping("/addWord")
        public String addWordToSection(@ModelAttribute("addWordForm") AddWordToSectionRequest addWordForm){
            String sectionName = addWordForm.getSectionName();
            String word = addWordForm.getWord();
            addWordForm.setSectionName(sectionName);
            addWordForm.setWord(word);
            sectionService.addWordToSection(addWordForm);
        return "redirect:/sections/all";
        }
        @GetMapping("/singleSection")
        public String getSingleSection(Model model,HttpServletRequest request,HttpSession session){
            String username = (String) session.getAttribute("username");
            String sectionName = request.getParameter("sectionName");
            List<String> wordsFromSection = userModelService.getWordsFromSection(username, sectionName);
            model.addAttribute("wordsInSection",wordsFromSection);
            model.addAttribute("sectionName",sectionName);
            return "single_section_view";
        }
}
