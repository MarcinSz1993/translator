package com.example.translator.service;

import com.example.translator.exception.SectionNotExistsException;
import com.example.translator.exception.UserNotFoundException;
import com.example.translator.model.Role;
import com.example.translator.model.Section;
import com.example.translator.model.UserModel;
import com.example.translator.repository.SectionRepository;
import com.example.translator.repository.UserRepository;
import com.example.translator.request.AddSectionRequest;
import com.example.translator.request.AddWordToSectionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SectionServiceTest {
    @InjectMocks
    private SectionService sectionService;
    @Mock
    private SectionRepository sectionRepository;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void shouldAddWordToSection() throws Throwable {
        AddWordToSectionRequest request = new AddWordToSectionRequest("EXAMPLE","word");
        List<String> words = new ArrayList<>();
        Section section = new Section(1,"EXAMPLE", words,null);
        List<String> expectedList = List.of("word");

        Mockito.when(sectionRepository.findByName(request.getSectionName())).thenReturn(Optional.of(section));
        Mockito.when(sectionRepository.save(section)).thenReturn(section);

        Section sectionAfterAddingWord = sectionService.addWordToSection(request);

        List<String> actualWords = sectionAfterAddingWord.getWords();

        Assertions.assertEquals(expectedList,actualWords);
        Assertions.assertEquals(expectedList.size(),actualWords.size());
    }

    @Test
    public void shouldThrowSectionNotExistExceptionWhenSectionNotExist(){
        AddWordToSectionRequest request = new AddWordToSectionRequest("Example","word");

        Mockito.when(sectionRepository.findByName(request.getSectionName())).thenThrow(SectionNotExistsException.class);

        Assertions.assertThrows(SectionNotExistsException.class, () -> sectionRepository.findByName(request.getSectionName()));

    }
    @Test
    public void shouldThrowSectionNotExistExceptionWithPersonalStatementWhenSectionNotExist(){
        AddWordToSectionRequest request = new AddWordToSectionRequest("Example","word");

        Mockito.when(sectionRepository.findByName(request.getSectionName())).thenThrow(new SectionNotExistsException(request.getSectionName()));
        SectionNotExistsException thrownException = Assertions.assertThrows(SectionNotExistsException.class, () -> sectionRepository.findByName(request.getSectionName()));

        Assertions.assertEquals("Sekcja o nazwie EXAMPLE nie istnieje.",thrownException.getMessage());
    }
    @Test
    public void shouldAddSection(){
        AddSectionRequest request = new AddSectionRequest("Section");
        Section expectedSection = new Section();
        expectedSection.setName(request.getSectionName());
        UserModel user = new UserModel(
                1,
                "Marcin",
                "Szabała",
                "Markinlol",
                "qwerty",
                "markinlol@wp.pl",
                Role.USER,
                null
        );

        String username = user.getUsername();

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        Mockito.when(sectionRepository.save(Mockito.any(Section.class))).thenAnswer(new Answer<Section>() {
            @Override
            public Section answer(InvocationOnMock invocation) throws Throwable {
                Section savedSection = invocation.getArgument(0);
                savedSection.setId(1);
                return savedSection;
            }
        });

        Section actualSection = sectionService.addSection(user, request);

        Assertions.assertEquals(expectedSection.getName(),actualSection.getName());
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenUserNotExist(){
        String notExistingUsername = "Lewandowski";

        Mockito.when(userRepository.findByUsername(notExistingUsername)).thenThrow(UserNotFoundException.class);

        Assertions.assertThrows(UserNotFoundException.class, () -> userRepository.findByUsername(notExistingUsername));
    }
}
