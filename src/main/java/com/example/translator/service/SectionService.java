package com.example.translator.service;

import com.example.translator.model.Section;
import com.example.translator.model.UserModel;
import com.example.translator.repository.SectionRepository;
import com.example.translator.repository.UserRepository;
import com.example.translator.request.AddSectionRequest;
import com.example.translator.request.AddWordToSectionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    public Section addSection(UserDetails userDetails, AddSectionRequest request) {
        Section section = new Section();
        section.setName(request.getSectionName());

        String username = userDetails.getUsername();
        Optional<UserModel> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            section.setUserModel(user.get());

            return sectionRepository.save(section);
        } else {

            throw new IllegalArgumentException("User not found");
        }
    }

    public Section addWordToSection(AddWordToSectionRequest request){
        Section section = sectionRepository.findByName(request.getSectionName()).orElseThrow();
        List<String> words = section.getWords();

        words.add(request.getWord());
        return sectionRepository.save(section);
    }

    public List<Section> getAllSections(){
        return sectionRepository.findAll();
    }
}
