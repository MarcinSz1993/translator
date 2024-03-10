package com.example.translator.restcontroller;

import com.example.translator.model.UserModel;
import com.example.translator.service.UserModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserModelController {

    private final UserModelService userModelService;

    @PostMapping("/")
    UserModel createUser(@RequestBody UserModel userModel){
        return userModelService.createUser(userModel);
    }
    @GetMapping("/words")
    List<String> wordsFromSection(@RequestParam String username,@RequestParam String sectionName){
        return userModelService.getWordsFromSection(username, sectionName);
    }
}
