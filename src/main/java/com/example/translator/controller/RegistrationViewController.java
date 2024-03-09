package com.example.translator.controller;

import com.example.translator.model.UserModel;
import com.example.translator.request.CreateUserRequest;
import com.example.translator.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationViewController {

    private final AuthenticationService authenticationService;

    @GetMapping("/registration")
    public String showRegistrationForm(@ModelAttribute("user") UserModel userModel) {
        return "register_form_view";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid CreateUserRequest request) {
        authenticationService.register(request);
        return "registration_success_view";
    }

}
