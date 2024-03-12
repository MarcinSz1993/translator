package com.example.translator.service;

import com.example.translator.exception.UserNotFoundException;
import com.example.translator.model.Section;
import com.example.translator.model.UserModel;
import com.example.translator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserModelService {

    private final UserRepository userRepository;

    public UserModel createUser(UserModel userModel){
        return userRepository.save(userModel);
    }

    public List<String> getWordsFromSection(String username,String sectionName){
        Optional<UserModel> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            UserModel userModel = user.get();
            List<Section> list = userModel.getSections().stream()
                    .filter(section -> section.getName().equals(sectionName))
                    .toList();
            return list.get(0).getWords();
        }
        else {
            throw new UserNotFoundException(username);
        }
    }
}
