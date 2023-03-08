package com.abdulahiTowhid.demo.Controller;

import com.abdulahiTowhid.demo.APIS.Recipe;
import com.abdulahiTowhid.demo.APIS.SpoonacularApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {
    @Autowired
    private SpoonacularApiClient apiClient;

    @GetMapping("/recipes/search")
    public List<Recipe> searchRecipes(@RequestParam String query) throws IOException {
        return apiClient.searchRecipes(query);
    }
}
