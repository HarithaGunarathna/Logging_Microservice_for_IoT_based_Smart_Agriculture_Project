package com.example.co227_project.User;


import com.example.co227_project.filters.RequestResponseLoggers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository productRepository) {
        this.userRepository = productRepository;
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userRepository.findAll());

    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){

        User user = new User();
        user.setUsername(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        user.setServerity(userRequest.getServerity());
        user.setType(userRequest.getType());

        return ResponseEntity.status(201).body(this.userRepository.save(user));
    }
}