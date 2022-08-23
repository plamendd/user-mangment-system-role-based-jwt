package com.example.springbootjwtauthorisation.service;

import com.example.springbootjwtauthorisation.model.User;
import com.example.springbootjwtauthorisation.security.UserPrincipal;
import com.example.springbootjwtauthorisation.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AutheticationServiceImpl implements  AuthenticationService{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtRefreshTokenService jwtRefreshTokenService;

    @Override
    public User signInAndReturnJWt(User signInRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );
        UserPrincipal userPrincipal =  (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);
        User signInUser = userPrincipal.getUser();
        signInUser.setAccessToken(jwt);
        signInUser.setRefreshToken(jwtRefreshTokenService.createRefreshToken(signInUser.getId()).getTokenId());

        return signInUser;
    }
}
