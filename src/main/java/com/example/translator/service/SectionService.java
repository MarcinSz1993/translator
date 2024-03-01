package com.example.translator.service;

import com.example.translator.model.Section;
import com.example.translator.model.UserModel;
import com.example.translator.repository.SectionRepository;
import com.example.translator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    public Section addSection(UserDetails userDetails, String name) {
        Section section = new Section();
        section.setName(name);

        String username = userDetails.getUsername();
        Optional<UserModel> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            section.setUserModel(user.get());

            return sectionRepository.save(section);
        } else {
            // Obsługa przypadku, gdy użytkownik nie istnieje
            throw new IllegalArgumentException("User not found");
        }
    }
}
