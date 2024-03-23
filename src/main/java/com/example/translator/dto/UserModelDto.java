package com.example.translator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
@Data
public class UserModelDto {
    @Length(min = 2,max = 15,message = "First name should have minimum 2 characters!")
    private String firstName;
    @Length(min = 2,max = 15,message = "Last name should have minimum 2 characters!")
    private String lastName;
    @Length(min = 4,max = 15,message = "Username should have minimum 4 characters!")
    private String username;
    @Length(min = 5,message = "Password cannot be shorter than 5 characters!")
    private String password;
    private String email;
}
