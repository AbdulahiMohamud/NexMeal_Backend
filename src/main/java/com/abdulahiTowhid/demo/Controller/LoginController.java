package com.abdulahiTowhid.demo.Controller;


import com.abdulahiTowhid.demo.Model.AppUser;
import com.abdulahiTowhid.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private UserRepo userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AppUser user) {


        try{
            AppUser existingUser = userRepository.findByEmailOrUserName(user.getEmail(), user.getUserName());
            String hashedUserPw = passwordEncoder.encode(user.getPassword());


            if (existingUser == null){
                return new ResponseEntity<>("Username or Email does not exists, please sign up or try again",HttpStatus.UNAUTHORIZED);
            } else if (passwordEncoder.matches(hashedUserPw, existingUser.getPassword())) {
                return new ResponseEntity<>("Wrong password, please try again",HttpStatus.UNAUTHORIZED);

            }
            return new ResponseEntity<>("User Found ,log in. Welcome back " + existingUser.getFirstName(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Error, please try again",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

