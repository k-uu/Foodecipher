package model;

import java.util.ArrayList;
import java.util.List;

// Represents a food recipe consisting of ingredients and their respective quantities
// INVARIANT: there is n core nutrients and n + 1 ingredients
public class Recipe {

    // EFFECTS: create a named recipe with Nutrition Facts with an empty ingredient and proportion list
    public Recipe(String recipeName, NutritionFacts nutritionFacts) { }

    // EFFECTS: create a named recipe with Nutrition Facts and a list of ingredient(s) and proportion(s)
    public Recipe(String recipeName, NutritionFacts nutritionFacts, List<Ingredient> ingredients) { }

    // MODIFIES: this
    // EFFECTS: adds a unique ingredient to the recipe
    public void addIngredient(Ingredient ingredient) {}


    // MODIFIES: this
    // EFFECTS: removes an existing ingredient from the recipe and returns true, else returns false
    public boolean removeIngredient(Ingredient ingredient) {
        return false; //stub
    }

    // MODIFIES: this
    // approximates the proportion of each ingredient in the recipe based on their individual nutrient / mass ratios
    // if the approximation finds a solution (consistent system) return true, else return false and do not mutate this
    public boolean findProportions() {
        return false; //stub
    }

    // EFFECTS: returns a list containing the decimal values of nutrient / mass ratios ordered with the first element
    // having the same Nutrient as the first in givenOrder
    private List<Double> toList(Ingredient ingredient, List<Nutrients> givenOrder) {
        return new ArrayList<>();
    }

    // EFFECTS: returns a list containing the decimal values of NutritionFacts ordered with the first element
    // having the same Nutrient as the first in givenOrder
    private List<Double> toList(NutritionFacts facts, List<Nutrients> givenOrder) {
        return new ArrayList<>();
    }


    // EFFECTS: returns a String for the recipe with the given format:
    // * recipeName *
    // ingredientName, Proportion: proportion
    // ...
    @Override
    public String toString() {
        return ""; //stub
    }
}
