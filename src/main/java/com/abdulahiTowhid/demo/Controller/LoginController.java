package com.abdulahiTowhid.demo.Controller;


import com.abdulahiTowhid.demo.Model.AppUser;
import com.abdulahiTowhid.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private UserRepo userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody AppUser user) {
//
//
//        try{
//            AppUser existingUser = userRepository.findByEmailOrUserName(user.getEmail(), user.getUsername());
//            String hashedUserPw = passwordEncoder.encode(user.getPassword());
//
//
//            if (existingUser == null){
//                return new ResponseEntity<>("Username or Email does not exists, please sign up or try again",HttpStatus.UNAUTHORIZED);
//            } else if (passwordEncoder.matches(hashedUserPw, existingUser.getPassword())) {
//                return new ResponseEntity<>("Wrong password, please try again",HttpStatus.UNAUTHORIZED);
//
//            }
//            return  ResponseEntity.ok().body(existingUser);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Unexpected Error, please try again",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    @PostMapping("logout")
    public ResponseEntity<String> logoutUser(){
        // invalidate current user session
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }

}

