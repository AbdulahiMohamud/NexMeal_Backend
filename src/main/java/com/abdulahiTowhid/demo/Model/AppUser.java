package com.abdulahiTowhid.demo.Model;

import com.abdulahiTowhid.demo.APIS.Recipe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.User;

import javax.crypto.SecretKey;
import javax.sql.DataSource;
import java.security.KeyStore;
import java.util.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "Users_Data")

public class AppUser implements UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role roles;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SavedRecipes> savedRecipes;


    @ManyToMany(mappedBy = "following")

    private Set<AppUser> followers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "followings",
            joinColumns = {@JoinColumn(name = "follower")},
            inverseJoinColumns = {@JoinColumn(name = "following")}
    )
    private Set<AppUser> following;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name())) ;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}


