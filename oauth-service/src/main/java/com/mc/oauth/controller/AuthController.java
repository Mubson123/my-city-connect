package com.mc.oauth.controller;

import com.mc.oauth.dto.SignInRequest;
import com.mc.oauth.dto.SignUpRequest;
import com.mc.oauth.models.Tokens;
import com.mc.oauth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Tokens> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        Tokens tokens = authService.signIn(signInRequest);
        return ResponseEntity.ok(tokens);
    }
}
