package com.example.spring_project_08;

import com.example.spring_project_08.repository.UserRepository;
import com.example.spring_project_08.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
}
