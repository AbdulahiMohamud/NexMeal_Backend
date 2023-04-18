package com.abdulahiTowhid.demo.Controller;

import com.abdulahiTowhid.demo.APIS.Recipe;
import com.abdulahiTowhid.demo.APIS.SpoonacularApiClient;
import com.abdulahiTowhid.demo.Model.*;
import com.abdulahiTowhid.demo.Repository.RecipeRepo;
import com.abdulahiTowhid.demo.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {
    @Autowired
    private SpoonacularApiClient apiClient;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RecipeRepo recipeRepo;


    @GetMapping("/recipe/saved/{userId}")
    public List<SavedRecipes> savedRecipe(@PathVariable Long userId){
        AppUser user = userRepo.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found"));

        return recipeRepo.findAllByAppUser(user);

    }


    @GetMapping("/recipes/search")
    public List<Recipe> searchRecipes(@RequestParam(value = "query", defaultValue = "pasta") String query,
                                      @RequestParam(value = "excludeIngredients", defaultValue = "pork,gelatin") String excludeIngredients
                                      ) throws IOException {
        return apiClient.searchRecipes(query,excludeIngredients);
    }
    @PostMapping("/users/{userId}/recipes")
    public SavedRecipes saveRecipe(@PathVariable Long userId, @RequestBody Recipe recipe) {
        AppUser user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        SavedRecipes savedRecipe = SavedRecipes.builder()
                .appUser(user)
                .recipes(recipe)
                .build();
        return recipeRepo.save(savedRecipe);
    }

    @DeleteMapping("/users/{userId}/recipes/{recipeId}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long userId, @PathVariable Long recipeId) {
        AppUser user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        SavedRecipes savedRecipe = recipeRepo.findById(recipeId).orElseThrow(() -> new EntityNotFoundException("Recipe not found"));
        if (!savedRecipe.getAppUser().equals(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        recipeRepo.delete(savedRecipe);
        return ResponseEntity.noContent().build();
    }

}
