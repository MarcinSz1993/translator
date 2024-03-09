package com.example.translator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class UserModelDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
}
