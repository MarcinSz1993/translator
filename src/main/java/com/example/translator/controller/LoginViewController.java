package com.example.translator.controller;

import com.example.translator.model.AuthenticationResponse;
import com.example.translator.model.UserModel;
import com.example.translator.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginViewController {

    private final AuthenticationService authenticationService;

    @GetMapping("/loginPage")
    public String showLoginPage(){
        return "login_view";
    }

    @PostMapping("/loginPage")
    public String login(@ModelAttribute("userModel")UserModel userModel, Model model){
        AuthenticationResponse authenticate = authenticationService.authenticate(userModel);
        String token = authenticate.getToken();
        model.addAttribute("token",token);


        return "login_successful_view";
    }
}
