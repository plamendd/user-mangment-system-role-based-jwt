package com.example.springbootjwtauthorisation.service;

import com.example.springbootjwtauthorisation.model.JwtRefreshToken;
import com.example.springbootjwtauthorisation.model.User;

public interface JwtRefreshTokenService {
    JwtRefreshToken createRefreshToken(Long userId);

    User generateAccessTokenFromRefreshToken(String refreshTokenId);
}
