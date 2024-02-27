package com.example.translator.service;

import com.example.translator.model.UserModel;
import com.example.translator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserModelService {

    private final UserRepository userRepository;

    public UserModel registerNewUser(UserModel userModel){
        return userRepository.save(userModel);
    }
}
