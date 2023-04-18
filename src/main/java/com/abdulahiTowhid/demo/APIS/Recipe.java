package com.abdulahiTowhid.demo.APIS;

import com.abdulahiTowhid.demo.Model.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Recipe {
    private int id;
    private int readyInMinutes;
    private String title;
    private String image;
    private String summary;
    private List<AnalyzedInstruction> analyzedInstructions;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private AppUser appUser;
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
