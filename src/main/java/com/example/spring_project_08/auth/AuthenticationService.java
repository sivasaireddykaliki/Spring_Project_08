package com.example.spring_project_08.auth;

import com.example.spring_project_08.entity.AuthenticationRequest;
import com.example.spring_project_08.entity.RegisterRequest;
import com.example.spring_project_08.entity.Token;
import com.example.spring_project_08.entity.User;
import com.example.spring_project_08.repository.TokenRepository;
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
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;


    private final PasswordEncoder passwordEncoder;


    private final JwtService jwtService;


    private final AuthenticationManager authenticationManager;




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


       // return token
        String jwtToken =  jwtService.generateToken(saved_user);

        saveUserToken(user,jwtToken);

        return jwtToken;
    }

    public void saveUserToken(User user, String jwtToken)
    {
        Token token = Token.builder()
                     .token_value(jwtToken)
                     .user(user)
                     .expired(false)
                     .build();

        tokenRepository.save(token);

    }

    public String login(AuthenticationRequest authenticationRequest)
    {

        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword());
        authenticationManager.authenticate(authentication);

        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        String jwtToken =  jwtService.generateToken(user);

        saveUserToken(user,jwtToken);

        return jwtToken;
    }
}
