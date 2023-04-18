package com.abdulahiTowhid.demo.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.abdulahiTowhid.demo.APIS.Recipe;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter(autoApply = true)
public class RecipeConverter implements AttributeConverter<Recipe, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Recipe recipe) {
        try {
            return objectMapper.writeValueAsString(recipe);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Unable to convert Recipe to JSON string", e);
        }
    }

    @Override
    public Recipe convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, Recipe.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to convert JSON string to Recipe", e);
        }
    }
}
