package com.example.springbootjwtauthorisation.repository;

import com.example.springbootjwtauthorisation.model.JwtRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRefreshTokenRepository  extends JpaRepository<JwtRefreshToken, String> {
}

