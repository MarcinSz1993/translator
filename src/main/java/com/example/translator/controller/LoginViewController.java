package com.example.translator.controller;

import com.example.translator.model.AuthenticationResponse;
import com.example.translator.model.UserModel;
import com.example.translator.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String login(UserModel userModel, HttpServletResponse response){
        AuthenticationResponse authenticate = authenticationService.authenticate(userModel);
        String token = authenticate.getToken();

        Cookie cookie = new Cookie("authToken",token);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "login_successful_view";
    }
}
