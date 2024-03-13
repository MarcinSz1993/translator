package com.example.translator.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){
        super("Użytkownik o nazwie lub z adresem email, który podałeś już istnieje!");
    }
}
