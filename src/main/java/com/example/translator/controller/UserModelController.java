package com.example.translator.controller;

import com.example.translator.model.UserModel;
import com.example.translator.service.UserModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserModelController {

    private final UserModelService userModelService;

    @PostMapping("/")
    UserModel createUser(@RequestBody UserModel userModel){
        return userModelService.createUser(userModel);
    }
}
