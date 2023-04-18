package com.abdulahiTowhid.demo.Repository;

import com.abdulahiTowhid.demo.APIS.Recipe;
import com.abdulahiTowhid.demo.Model.AppUser;
import com.abdulahiTowhid.demo.Model.SavedRecipes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepo extends JpaRepository<SavedRecipes,Long> {
    List<SavedRecipes> findAllByAppUser(AppUser appUser);}
