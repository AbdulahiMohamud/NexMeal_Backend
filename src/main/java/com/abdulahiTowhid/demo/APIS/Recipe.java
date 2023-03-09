package com.abdulahiTowhid.demo.APIS;

import lombok.Data;

import java.util.List;

@Data
public class Recipe {
    private int id;
    private int readyInMinutes;
    private String title;
    private String image;
    private String summary;
    private List<AnalyzedInstruction> analyzedInstructions;
}

@Data
class AnalyzedInstruction {
    private String name;
    private List<Step> steps;
}

@Data
class Step {
    private int number;
    private String step;
    private List<Ingredient> ingredients;
    private List<Equipment> equipment;
}

@Data
class Ingredient {
    private int id;
    private String name;
    private String localizedName;
    private String image;
}

@Data
class Equipment {
    private int id;
    private String name;
    private String localizedName;
    private String image;
}
