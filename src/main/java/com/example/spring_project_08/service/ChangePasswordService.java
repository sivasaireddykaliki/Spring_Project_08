package com.example.spring_project_08.service;

import com.example.spring_project_08.entity.ChangePassword;
import com.example.spring_project_08.entity.User;
import com.example.spring_project_08.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/changePassword")
    public ResponseEntity<String> passwordChange(ChangePassword changePassword)
    {
        Optional<User> user_=userRepository.findByEmail(changePassword.getEmail());
        if(user_.isPresent())
        {
            User user=user_.get();
            String userPassword= user.getPassword();

            if(BCrypt.checkpw(changePassword.getCurrentPassword(),userPassword)) // if input password matched with password from database chnage it
            {
                if((changePassword.getNewPassword()).equals(changePassword.getConfirmPassword()))
                {
                    user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
                    System.out.println("New Password for email "+user.getEmail()+" is "+changePassword.getNewPassword());
                    userRepository.save(user);
                    return new ResponseEntity<>("Password Change is successful", HttpStatus.OK);

                }
                else {
                    return new ResponseEntity<>("New Password should match with confirm Password",HttpStatus.OK);
                }

            }

            else {
                return new ResponseEntity<>("Enter correct user password",HttpStatus.OK);
            }

        }
        else {
            return new ResponseEntity<>("User not found",HttpStatus.OK);
        }
    }
}
