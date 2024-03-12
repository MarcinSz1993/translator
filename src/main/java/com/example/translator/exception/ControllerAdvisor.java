package com.example.translator.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFoundExceptionHandler(UserNotFoundException ex){
        ModelAndView modelAndView = new ModelAndView("error/user_not_found");
        modelAndView.addObject("message",ex.getMessage());
        return modelAndView;
    }
}
