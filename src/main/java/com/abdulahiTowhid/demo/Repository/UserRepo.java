package com.abdulahiTowhid.demo.Repository;

import com.abdulahiTowhid.demo.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <AppUser, Long> {
    AppUser findByEmail (String email);
    AppUser findByUserName(String userName);



}
