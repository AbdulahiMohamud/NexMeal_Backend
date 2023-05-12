package com.abdulahiTowhid.demo.Controller;



import com.abdulahiTowhid.demo.Model.AppUser;
import com.abdulahiTowhid.demo.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepo userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;



    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupUser user){
        var existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail != null) {
            return new ResponseEntity<>("Email " + user.getEmail() +" already exists", HttpStatus.CONFLICT);
        }
        var existingUserByUsername = userRepository.findByUserName(user.getUserName());
        if (existingUserByUsername != null) {
            return new ResponseEntity<>("Username " + user.getUserName() + " already exists",HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(authenticationService.signup(user));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateUser user){


        try{
            AppUser existingUser = userRepository.findByEmail(user.getEmail());



            if (existingUser == null){
                return new ResponseEntity<>("Wrong Email or password, please sign up or try again",HttpStatus.UNAUTHORIZED);
            } if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())
            ) {
                return new ResponseEntity<>("Wrong password, please try again",HttpStatus.UNAUTHORIZED);

            }
            AuthenticationResponce authResponse = authenticationService.authenticate(user);
            return ResponseEntity.ok(new Object[]{authResponse,existingUser});
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected Error, please try again",HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    @PostMapping("follow/{userID}")
    public ResponseEntity<?> followUser(@RequestBody AuthenticateUser user , @PathVariable Long userID ) {
        Optional<AppUser> publicOptional = userRepository.findById(userID);
        AppUser currentUser = userRepository.findByEmail(user.getEmail());

        AppUser publicUser = publicOptional.get();

        if (publicUser == null || currentUser == null) {
            return ResponseEntity.notFound().build();
        }

        if (publicUser.equals(currentUser)) {
            return ResponseEntity.badRequest().body("You can't follow yourself.");
        }

        Set<AppUser> following = currentUser.getFollowing();
        if (following.contains(publicUser)) {
            return ResponseEntity.badRequest().body("You are already following " + publicUser.getUsername() + ".");
        }

        currentUser.getFollowing().add(publicUser);


        userRepository.save(currentUser);



        return ResponseEntity.ok().build();
    }

    @GetMapping("/feed")
    public List<AppUser> feed(){
        return userRepository.findAll();
    }








}


