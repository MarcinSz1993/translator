package com.example.translator.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateUserRequest {

    @NotEmpty
    @Length(min = 2,max = 15)
    private String firstName;
    @NotEmpty
    @Length(min = 2,max = 15)
    private String lastName;
    @NotEmpty
    @Length(min = 4,max =15)
    private String username;
    @NotEmpty
    @Length(min = 5)
    private String password;
    @NotEmpty
    @Email
    private String email;
}
