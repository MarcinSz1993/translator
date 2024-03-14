package com.example.translator.repository;

import com.example.translator.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section,Integer> {

    Optional<Section> findByName(String sectionName);
    List<Section>findAllByUserModel_Username(String username);

}
