package com.example.spring_project_08.controller;

import com.example.spring_project_08.entity.Role;
import com.example.spring_project_08.entity.User;
import com.example.spring_project_08.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable("id") int id){
        return userService.updateUserById(user,id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") int id)
    {
        return userService.deleteUserById(id);
    }



}
