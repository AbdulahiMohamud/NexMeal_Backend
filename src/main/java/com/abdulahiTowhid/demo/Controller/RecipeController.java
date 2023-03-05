package com.abdulahiTowhid.demo.Controller;

import com.abdulahiTowhid.demo.APIS.Recipe;
import com.abdulahiTowhid.demo.APIS.SpoonacularApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public class RecipeController {
    @Autowired
    private SpoonacularApiClient apiClient;

    @GetMapping("/recipes/search")
    public List<Recipe> searchRecipes(@RequestParam String query) throws IOException {
        return apiClient.searchRecipes(query);
    }
}
