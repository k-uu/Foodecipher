package model;

import java.util.ArrayList;
import java.util.HashMap;

// Represents a list of food recipes
public class RecipeList {

    // EFFECTS: initializes an empty recipe list
    public RecipeList() { }

    // MODIFIES: this
    // EFFECTS: adds a recipe to the recipe list
    public void addRecipe(Recipe recipe) { }

    // MODIFIES: this
    // EFFECTS: adds a recipe to the recipe list and returns true. If the recipe is not in list return false
    public boolean removeRecipe(Recipe recipe) {
        return false; //stub
    }

    // REQUIRES: the recipeName exists in the RecipeList
    // EFFECTS : returns the Recipe with the given recipeName
    public Recipe getRecipe(String recipeName) {
        return new Recipe("",
                new NutritionFacts(10, new HashMap<>()), new ArrayList<>()); //stub
    }



    // EFFECTS: returns a String representation of the recipe list with the given format:
    // * RECIPES *
    // recipeName
    // recipeName
    //...
    @Override
    public String toString() {
        return ""; //stub
    }
}
