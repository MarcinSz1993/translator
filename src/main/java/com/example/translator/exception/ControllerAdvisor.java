package com.example.translator.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(BadCredentialsException.class)
    public ModelAndView handleBadCredentialsException(BadCredentialsException ex) {
        ModelAndView modelAndView = new ModelAndView("error/bad_credentials");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        ModelAndView modelAndView = new ModelAndView("/error/user_already_exists");
        modelAndView.addObject("message",ex.getMessage());
        return modelAndView;
    }
}
