package com.example.translator.service;

import com.example.translator.exception.SectionNotExistsException;
import com.example.translator.exception.UserNotFoundException;
import com.example.translator.model.Role;
import com.example.translator.model.Section;
import com.example.translator.model.UserModel;
import com.example.translator.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserModelServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserModelService userModelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getWordsFromSectionWhenInputIsCorrect() {
        String userName = "Kowal";
        String sectionName = "Różne";

        List<String> expectedList = List.of("Dog", "Cat");
        List<String> words = List.of("Dog", "Cat");

        UserModel userModel = new UserModel(
                1,
                "Jan",
                "Kowalski",
                "Kowal",
                "qwerty",
                "jan@kowalski.pl",
                Role.USER,
                List.of(new Section(1, "Różne", words, null))
        );

        Mockito.when(userRepository.findByUsername(userName)).thenReturn(Optional.of(userModel));
        List<String> wordsFromSection = userModelService.getWordsFromSection(userName, sectionName);

        Assertions.assertEquals(expectedList, wordsFromSection);
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(userName);
    }

    @Test
    public void getWordsFromSectionShouldThrowUserNotFoundExceptionWhenUserNotExist() {
        String username = "Dirty-Joe";

        Mockito.when(userRepository.findByUsername(username)).thenThrow(UserNotFoundException.class);
        Assertions.assertThrows(UserNotFoundException.class, () -> userModelService.getWordsFromSection(username, "Różne"));
    }
    @Test
    public void getWordsFromSectionShouldThrowSectionNotExistsExceptionWhenSectionNotExists(){
        String sectionName = "Pogoda";
        String userName = "Kowal";
        UserModel userModel = new UserModel(
                1,
                "Jan",
                "Kowalski",
                "Kowal",
                "qwerty",
                "jan@kowalski.pl",
                Role.USER,
                Collections.emptyList()
        );

        Mockito.when(userRepository.findByUsername(userName)).thenReturn(Optional.of(userModel));
        Assertions.assertThrows(SectionNotExistsException.class, () -> userModelService.getWordsFromSection(userName,sectionName));
    }
    @Test
    public void getWordsFromSectionShouldThrowUserNotFoundExceptionWithPersonalStatement() {
        String username = "DirtyJoe";

        Mockito.when(userRepository.findByUsername(username)).thenThrow(new UserNotFoundException(username));
        UserNotFoundException userNotFoundException = Assertions.assertThrows(UserNotFoundException.class, () -> userRepository.findByUsername(username));

        Assertions.assertEquals("User with the username: " + username + " does not exist!",userNotFoundException.getMessage());
    }

    @Test
    public void getWordsFromSectionShouldThrowSectionNotExistsExceptionWithStatement(){
        String sectionName = "Pogoda";
        String userName = "Kowal";
        UserModel userModel = new UserModel(
                1,
                "Jan",
                "Kowalski",
                "Kowal",
                "qwerty",
                "jan@kowalski.pl",
                Role.USER,
                Collections.emptyList()
        );
        Mockito.when(userRepository.findByUsername(userName)).thenReturn(Optional.of(userModel));
        SectionNotExistsException sectionNotExistsException = Assertions.assertThrows(SectionNotExistsException.class, () -> userModelService.getWordsFromSection(userName, sectionName));
        Assertions.assertEquals("Sekcja o nazwie " +  sectionName.toUpperCase() + " nie istnieje.",sectionNotExistsException.getMessage());
    }


}
