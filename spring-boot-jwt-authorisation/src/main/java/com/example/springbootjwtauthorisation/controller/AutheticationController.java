package com.example.springbootjwtauthorisation.controller;


import com.example.springbootjwtauthorisation.model.User;
import com.example.springbootjwtauthorisation.service.AuthenticationService;
import com.example.springbootjwtauthorisation.service.JwtRefreshTokenService;
import com.example.springbootjwtauthorisation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authentication")
public class AutheticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtRefreshTokenService jwtRefreshTokenService;

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user){
        if (userService.findByUsername(user.getUsername()).isPresent()){
            return  new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return  new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user){
        return new ResponseEntity<>(authenticationService.signInAndReturnJWt(user), HttpStatus.OK);
    }

    @PostMapping("refresh-token")
    public ResponseEntity<?> refreshToken(@RequestParam String token){
        return ResponseEntity.ok(jwtRefreshTokenService.generateAccessTokenFromRefreshToken(token));
    }
}
