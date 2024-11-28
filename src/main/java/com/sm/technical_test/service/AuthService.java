package com.sm.technical_test.service;

import com.sm.technical_test.model.request.AuthRequest;
import com.sm.technical_test.model.response.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);
}
