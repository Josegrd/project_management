package com.sm.technical_test.service.impl;

import com.sm.technical_test.entity.UserCredential;
import com.sm.technical_test.repository.UserCredentialRepository;
import com.sm.technical_test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserCredentialRepository userCredentialRepository;

//    public UserDetails loadByUserId(String userId) {
//        return userCredentialRepository.findById(userId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
//    }


    @Override
    public UserCredential loadByUserId(String userId) {
        return userCredentialRepository.findById(userId).orElse(null);
    }

    @Override
    public UserCredential findByUsername(String username) {
        return userCredentialRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserCredential loadUserByUsername(String username) throws UsernameNotFoundException {
        return userCredentialRepository.findByUsername(username).orElse(null);
    }
}
