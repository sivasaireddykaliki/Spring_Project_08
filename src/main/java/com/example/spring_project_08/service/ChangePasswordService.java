package com.example.spring_project_08.service;

import com.example.spring_project_08.auth.AuthController;
import com.example.spring_project_08.entity.ChangePassword;
import com.example.spring_project_08.entity.User;
import com.example.spring_project_08.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(ChangePasswordService.class);

    @PostMapping("/changePassword")
    public ResponseEntity<String> passwordChange(ChangePassword changePassword)
    {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) auth.getPrincipal();

        logger.info("Performing password change operation");

        // Optional<User> user_=userRepository.findByEmail(principal.getEmail());

        String userPassword= principal.getPassword();

        if(passwordEncoder.matches(changePassword.getCurrentPassword(),userPassword)) // if input password matched with password from database chnage it
        {
            logger.info("Current Password matches with user Password");

            if ((changePassword.getNewPassword()).equals(changePassword.getConfirmPassword())) {

                logger.info("New Password matches with Confirm Password");
                principal.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
                userRepository.save(principal);
                logger.info("Password Change operation successful");
                return new ResponseEntity<>("Password Change is successful", HttpStatus.OK);

            } else {
                logger.error("New Password should match with confirm Password");
                return new ResponseEntity<>("New Password should match with confirm Password", HttpStatus.OK);
            }
        }

            else {
                logger.error("Current Password should match with user password");
                return new ResponseEntity<>("Enter correct user password",HttpStatus.OK);
            }

    }
}
