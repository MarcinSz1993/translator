package com.example.translator.exception;

public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException(){
        super("Wpisałeś niepoprawny login lub hasło!");
    }
}
