package com.abdulahiTowhid.demo.Controller;


import com.abdulahiTowhid.demo.Model.AppUser;
import com.abdulahiTowhid.demo.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserRepo userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AppUser user) {

        // Check if the user already exists using email
        AppUser existingUser = userRepository.findByEmail(user.getEmail());
        AppUser existingUserByUsername = userRepository.findByUserName(user.getUserName());
        if (existingUser != null ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("User with email " + user.getEmail() +
                    " already exists"));
        } else if (existingUserByUsername != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Username " + user.getUserName() +
                    " was taken, please create a new one."));

        }

        // Create a new user
        AppUser newUser = new AppUser();
        newUser.setUserName(user.getUserName());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }
}

