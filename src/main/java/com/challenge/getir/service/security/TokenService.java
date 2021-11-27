package com.challenge.getir.service.security;

import com.challenge.getir.model.User;
import com.challenge.getir.model.UserPrincipal;

public interface TokenService {
    String generateToken(User user);

    UserPrincipal parseToken(String token);
}