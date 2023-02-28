package com.abdulahiTowhid.demo.APIS;

import com.abdulahiTowhid.demo.Model.AppUser;
import com.abdulahiTowhid.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GetMethods {
    @Autowired
    private UserRepo userRepo;


    @GetMapping("/api/data")
    public List<AppUser> seeAppUser(){

        return userRepo.findAll();

    }






}
