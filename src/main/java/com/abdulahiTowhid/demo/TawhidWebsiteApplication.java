package com.abdulahiTowhid.demo;

import com.abdulahiTowhid.demo.APIS.Recipe;
import com.abdulahiTowhid.demo.APIS.SpoonacularApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class TawhidWebsiteApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TawhidWebsiteApplication.class, args);

		TestApi();


	}

	public static void TestApi() throws IOException {
		SpoonacularApiClient client = new SpoonacularApiClient();
		List<Recipe> recipes = client.searchRecipes("chicken");
		for (Recipe recipe : recipes) {
			System.out.println(recipe.getTitle());
		}
	}




}
