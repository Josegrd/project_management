package com.sm.technical_test.service.impl;

import com.sm.technical_test.constant.ERole;
import com.sm.technical_test.entity.Role;
import com.sm.technical_test.entity.UserCredential;
import com.sm.technical_test.model.request.AuthRequest;
import com.sm.technical_test.model.response.AuthResponse;
import com.sm.technical_test.repository.UserCredentialRepository;
import com.sm.technical_test.security.JwtUtils;
import com.sm.technical_test.service.AuthService;
import com.sm.technical_test.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Value("admin")
    private String username;
    @Value("admin123")
    private String password;

    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void initSuperAdmin() {
        Optional<UserCredential> optionalUserCredential = userCredentialRepository.findByUsername(username);
        if (optionalUserCredential.isPresent()) return;
        Role adminRole = roleService.getOrSave(ERole.ROLE_ADMIN);
        String hashedPassword = passwordEncoder.encode(password);
        UserCredential userCredential = UserCredential.builder()
                .username(username)
                .password(hashedPassword)
                .role(adminRole)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        userCredentialRepository.save(userCredential);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            );
            System.out.println("Auth : " + authentication);
            System.out.println("username : " + request.getUsername());
            System.out.println("password : " + request.getPassword());
            Authentication authenticated = authenticationManager.authenticate(authentication);
            System.out.println("Authenticated : " + authenticated);
            SecurityContextHolder.getContext().setAuthentication(authenticated);
            UserCredential userCredential = (UserCredential) authenticated.getPrincipal();

//            Object principal = authentication.getPrincipal();
//            if (!(principal instanceof UserCredential)) {
//                throw new RuntimeException("Principal is not an instance of UserCredential");
//            }
//            UserCredential userCredential = (UserCredential) principal;
            String token = jwtUtils.generateToken(userCredential);
            return AuthResponse.builder()
                    .message("Success Login")
                    .token(token)
                    .build();
        } catch (Exception e) {
            return AuthResponse.builder()
                    .message("Invalid username and password")
                    .build();
        }
    }
}
