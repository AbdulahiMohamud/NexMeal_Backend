package com.abdulahiTowhid.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import javax.crypto.SecretKey;
import java.security.KeyStore;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table (name = "Users_Data")

public class AppUser {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    public AppUser(String userName, String firstName, String lastName, String email, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
