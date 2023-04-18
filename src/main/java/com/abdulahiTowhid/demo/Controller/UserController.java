package com.abdulahiTowhid.demo.Controller;



import com.abdulahiTowhid.demo.Model.AppUser;
import com.abdulahiTowhid.demo.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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




    }


