package com.abdulahiTowhid.demo.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.security.core.userdetails.User;

import javax.crypto.SecretKey;
import javax.sql.DataSource;
import java.security.KeyStore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    public AppUser(String userName, String firstName, String lastName, String email, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    public void addRoles (Role role) {
        this.roles.add(role);
        role.getUsers().add(this);

    }
    public void removeRoles (Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}


