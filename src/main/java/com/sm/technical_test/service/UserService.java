package com.sm.technical_test.service;

import com.sm.technical_test.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserCredential loadByUserId(String userId);
    UserCredential findByUsername(String username);
}
