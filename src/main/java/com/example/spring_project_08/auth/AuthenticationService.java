package com.example.spring_project_08.auth;

import com.example.spring_project_08.entity.AuthenticationRequest;
import com.example.spring_project_08.entity.RegisterRequest;
import com.example.spring_project_08.entity.User;
import com.example.spring_project_08.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }



    public String register(RegisterRequest request)
    {
       // Initialize user
        User user= User.builder()
                  .firstName(request.getFirstName())
                  .lastName(request.getLastName())
                  .password(passwordEncoder.encode(request.getPassword()))
                  .email(request.getEmail())
                  .role(request.getRole())
                  .build();

        //save user in user db
        User saved_user= userRepository.save(user);

       // return username
        return jwtService.generateToken(saved_user);

        // we''ll generate token tomorrow
    }

    public String login(AuthenticationRequest authenticationRequest)
    {

        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword());
        authenticationManager.authenticate(authentication);

        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        return jwtService.generateToken(user);
    }
}
