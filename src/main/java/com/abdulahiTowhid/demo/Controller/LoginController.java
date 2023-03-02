package com.abdulahiTowhid.demo.Controller;


import com.abdulahiTowhid.demo.Model.AppUser;
import com.abdulahiTowhid.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowiredgit
    private UserRepo userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AppUser user) {
        AppUser existingUserByEmail = userRepository.findByEmail(user.getEmail());
        AppUser existingUserByUsername = userRepository.findByUserName(user.getUserName());

        if (existingUserByEmail != null || existingUserByUsername != null) {
            return new ResponseEntity<>("User does not exist, please sign up.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("User found, logging in.", HttpStatus.OK);
        }
    }
}

