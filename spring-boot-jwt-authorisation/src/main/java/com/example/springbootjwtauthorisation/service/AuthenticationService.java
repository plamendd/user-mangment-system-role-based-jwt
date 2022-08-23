package com.example.springbootjwtauthorisation.service;

import com.example.springbootjwtauthorisation.model.User;

public interface AuthenticationService {
    User signInAndReturnJWt(User signInRequest);
}
