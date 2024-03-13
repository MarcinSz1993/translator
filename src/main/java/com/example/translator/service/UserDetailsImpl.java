package com.example.translator.service;

import com.example.translator.exception.BadCredentialsException;
import com.example.translator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        return userRepository.findByUsername(username)
                .orElseThrow(BadCredentialsException::new);
    }
}
