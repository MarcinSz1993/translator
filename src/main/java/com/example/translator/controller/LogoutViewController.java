package com.example.translator.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LogoutViewController {



    @PostMapping("/logoutPage")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute("username");
        session.invalidate();

        Cookie cookie = new Cookie("auth_token",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/loginPage";
    }
}


//    @GetMapping("/logoutPage")
//    public String showLogoutPage(){
//        return "logout_view";
//    }
