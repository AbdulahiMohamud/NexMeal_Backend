package com.abdulahiTowhid.demo.APIS;

import com.google.gson.Gson;
import lombok.Data;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.List;


@ConfigurationProperties
public class SpoonacularApiClient {



    public  String API_KEY;
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/";


    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Autowired
    public SpoonacularApiClient( @Value("${key.API_KEY}") String API_KEY) {
        this.API_KEY = API_KEY;

    }

    public List<Recipe> searchRecipes(String query, String excludeIngredients) throws IOException{
        String url = BASE_URL + "complexSearch?apiKey=" + API_KEY + "&query=" + query +
                "&excludeIngredients=" + excludeIngredients + "&addRecipeInformation=true";
        Request request = new Request.Builder()
                .url(url)
                .build();
        try(Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            SearchResults searchResult = gson.fromJson(responseBody,SearchResults.class);
            return searchResult.getResults();

        }
    }
    @Data
    private static class SearchResults{

        private List<Recipe> results;

    }
}
