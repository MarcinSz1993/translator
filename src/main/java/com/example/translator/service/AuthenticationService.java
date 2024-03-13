package com.example.translator.service;

import com.example.translator.exception.BadCredentialsException;
import com.example.translator.exception.UserAlreadyExistsException;
import com.example.translator.mapper.UserModelMapper;
import com.example.translator.model.AuthenticationResponse;
import com.example.translator.model.Role;
import com.example.translator.model.UserModel;
import com.example.translator.repository.UserRepository;
import com.example.translator.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(CreateUserRequest request) {

        if(userRepository.existsByEmail(request.getEmail()) ||
                userRepository.existsByUsername(request.getUsername())){
            throw new UserAlreadyExistsException();
        }

        UserModel user = new UserModel();
        user.setFirstName(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setSections(Collections.emptyList());
        user.setRole(Role.valueOf("USER"));

        user = userRepository.save(user);
        String token = jwtService.generateToken(user);

        UserModelMapper.convertUserModelToUserModelDto(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(UserModel request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()));
            Optional<UserModel> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            UserModel userModel = user.get();
            String token = jwtService.generateToken(userModel);
            return new AuthenticationResponse(token);
        } else {
            throw new BadCredentialsException();
        }

        } catch (AuthenticationException exception) {
            throw new BadCredentialsException();
        }
    }

}
