package com.example.translator.controller;

import com.example.translator.exception.UserNotFoundException;
import com.example.translator.model.AuthenticationResponse;
import com.example.translator.model.UserModel;
import com.example.translator.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LoginViewController {

    private final AuthenticationService authenticationService;

    @GetMapping("/loginPage")
    public String showLoginPage(){
        return "login_view";
    }
    //if(userModel == null){
   //     throw new UserNotFoundException(userModel.getUsername());
   // }

    @PostMapping("/loginPage")
    public String login(UserModel userModel, HttpServletResponse response, HttpSession session){
        try {
            AuthenticationResponse authenticate = authenticationService.authenticate(userModel);
            String token = authenticate.getToken();

            Cookie cookie = new Cookie("authToken",token);
            cookie.setPath("/");
            response.addCookie(cookie);
            session.setAttribute("username",userModel.getUsername());

            return "login_successful_view";
        } catch (UserNotFoundException ex) {
            ModelAndView modelAndView = new ModelAndView("error/user_not_found");
            modelAndView.addObject("message",ex.getMessage());
        }
        return "error/user_not_found";
    }

}
