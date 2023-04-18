package com.abdulahiTowhid.demo.Controller;

import com.abdulahiTowhid.demo.Model.AppUser;
import com.abdulahiTowhid.demo.Model.Role;
import com.abdulahiTowhid.demo.Repository.UserRepo;
import com.abdulahiTowhid.demo.config.JwtService;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    UserRepo userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public AuthenticationResponce signup(SignupUser user) {

        var appUser = AppUser.builder()
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(Role.USER)
                .build();
        userRepository.save(appUser);
        var jwtToken = jwtService.generateToken(appUser);
        return AuthenticationResponce.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponce authenticate(AuthenticateUser user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        ));
        var appUser = userRepository.findByEmail(user.getEmail());
        var jwtToken = jwtService.generateToken(appUser);
        return AuthenticationResponce.builder()
                .token(jwtToken)
                .build();
    }
}
