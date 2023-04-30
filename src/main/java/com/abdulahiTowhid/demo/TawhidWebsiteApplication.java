package com.abdulahiTowhid.demo;

import com.abdulahiTowhid.demo.APIS.Recipe;
import com.abdulahiTowhid.demo.APIS.SpoonacularApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(SpoonacularApiClient.class)
public class TawhidWebsiteApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TawhidWebsiteApplication.class, args);

		TestApi();


	}

	// test to see if am able to get data from the api
	// works need to change code around

	public static void TestApi() throws IOException {
		SpoonacularApiClient client = new SpoonacularApiClient();
		List<Recipe> recipes = client.searchRecipes("chicken","fish");
		for (Recipe recipe : recipes) {
//			System.out.println(recipe.getAnalyzedInstructions());
		}
	}




}
