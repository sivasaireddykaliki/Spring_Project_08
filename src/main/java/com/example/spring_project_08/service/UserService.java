package com.example.spring_project_08.service;

import com.example.spring_project_08.entity.Role;
import com.example.spring_project_08.entity.User;
import com.example.spring_project_08.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //get list of users
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    public ResponseEntity<User> getUserById(int id)
    {
        Optional<User> user_=userRepository.findById(id);
        if(user_.isPresent())
        {
            User user=user_.get();

            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //update user by id
    public ResponseEntity<User> updateUserById(User user, int id)
    {
        Optional<User> user_=userRepository.findById(id);
        if(user_.isPresent())
        {
            User updateUser=user_.get();
            updateUser.setRole(user.getRole());

            return new ResponseEntity<>(userRepository.save(updateUser), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // delete user by id
    public ResponseEntity<String> deleteUserById(int id)
    {
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent())
        {
            userRepository.deleteById(id);

            return new ResponseEntity<>("User with id-"+id+" is deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
        }
    }

}
