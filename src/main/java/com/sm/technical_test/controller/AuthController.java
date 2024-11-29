package com.sm.technical_test.controller;


import com.sm.technical_test.model.request.AuthRequest;
import com.sm.technical_test.model.response.AuthResponse;
import com.sm.technical_test.model.response.WebResponse;
import com.sm.technical_test.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest){
        try{
            AuthResponse token = authService.login(authRequest);
            WebResponse<AuthResponse> response = WebResponse.<AuthResponse>builder()
                    .status(HttpStatus.OK.getReasonPhrase())
                    .message(token.getMessage())
                    .data(token)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            WebResponse<String> errorResponse = WebResponse.<String>builder()
                    .status("ERROR")
                    .message(e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
