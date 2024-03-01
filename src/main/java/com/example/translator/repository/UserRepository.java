package com.example.translator.repository;

import com.example.translator.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {

    Optional<UserModel> findByUsername(String username);

}
