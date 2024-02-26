package com.example.spring_project_08.auth;

import com.example.spring_project_08.entity.AuthenticationRequest;
import com.example.spring_project_08.entity.AuthenticationResponse;
import com.example.spring_project_08.entity.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

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

}

//requestMatcher("/api/auth/**").permitAll()
