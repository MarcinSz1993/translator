package com.example.translator.exception;

public class SectionNotExistsException extends RuntimeException {
    public SectionNotExistsException(String sectionName){
        super("Sekcja o nazwie: " + sectionName + "nie istnieje.");
    }
}
