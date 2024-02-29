package com.example.spring_project_08.auth;

import ch.qos.logback.classic.LoggerContext;
import com.example.spring_project_08.entity.*;
import com.example.spring_project_08.repository.UserRepository;
import com.example.spring_project_08.service.ChangePasswordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ChangePasswordService changePasswordService;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest)
    {
        logger.info("Registering new user");

        String token=authenticationService.register(registerRequest);

        logger.info("User Registered successfully with token assigned");

        AuthenticationResponse response = new AuthenticationResponse(token); // token gets username through constructor

        logger.info("Receive token as response after user registration");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest)
    {
        logger.info("Performing login operation ");

        String token  = authenticationService.login(authenticationRequest);

        logger.info("Login successful ");

        AuthenticationResponse response = new AuthenticationResponse(token);

        logger.info("Token is received after user login");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> passwordChange(@RequestBody ChangePassword changePassword)
    {
        logger.info("Call service layer for Password Change operation");

        return changePasswordService.passwordChange(changePassword);
    }

}

//requestMatcher("/api/auth/**").permitAll()
