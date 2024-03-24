package com.example.translator.service;

import com.example.translator.exception.SectionNotExistsException;
import com.example.translator.exception.UserNotFoundException;
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
import java.util.function.Supplier;

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

            throw new UserNotFoundException(username);
        }
    }

    public Section addWordToSection(AddWordToSectionRequest request) throws Throwable {
        Section section = sectionRepository.findByName(request.getSectionName()).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new SectionNotExistsException(request.getSectionName());
            }
        });
        List<String> words = section.getWords();

        words.add(request.getWord());
        return sectionRepository.save(section);
    }

    public List<Section> getAllSections(String username){
        return sectionRepository.findAllByUserModel_Username(username);
    }

}
