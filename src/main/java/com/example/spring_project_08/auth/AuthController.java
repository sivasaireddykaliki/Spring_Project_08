package com.example.spring_project_08.auth;

import com.example.spring_project_08.entity.*;
import com.example.spring_project_08.repository.UserRepository;
import com.example.spring_project_08.service.ChangePasswordService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest)
    {
        String token=authenticationService.register(registerRequest);

        AuthenticationResponse response = new AuthenticationResponse(token); // token gets username through constructor

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest)
    {
        String token  = authenticationService.login(authenticationRequest);

        AuthenticationResponse response = new AuthenticationResponse(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> passwordChange(@RequestBody ChangePassword changePassword)
    {
        return changePasswordService.passwordChange(changePassword);
    }

}

//requestMatcher("/api/auth/**").permitAll()
