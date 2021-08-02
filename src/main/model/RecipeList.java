package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of food recipes
public class RecipeList implements Writable {

    private List<Recipe> recipes;

    // EFFECTS: initializes an empty recipe list
    public RecipeList() {

        recipes = new ArrayList<>();
    }

    // REQUIRES: the recipe's name is not already in the list;
    // MODIFIES: this
    // EFFECTS: adds a recipe to the recipe list
    public void addRecipe(Recipe recipe) {

        recipes.add(recipe);
    }


    // MODIFIES: this
    // EFFECTS: removes a recipe from the recipe list and returns true. If the recipe is not in list return false
    public boolean removeRecipe(Recipe recipe) {
        for (Recipe r : recipes) {
            if (recipe.getName().equals(r.getName())) {
                recipes.remove(r);
                return true;
            }
        }
        return false;
    }


    // EFFECTS: returns a String representation of the recipe list with the given format:
    // * RECIPES *
    // recipeName
    // recipeName
    //...
    @Override
    public String toString() {

        String result = "* RECIPES *";
        for (Recipe r : recipes) {
            result = result.concat(System.lineSeparator() + r.getName());
        }
        return result;
    }

    //getters
    public int getRecipeCount() {

        return recipes.size();
    }

    public List<Recipe> getRecipes() {

        return new ArrayList<>(recipes);
    }

    // EFFECTS: returns recipes as a JSON array
    @Override
    public JSONObject toJson() {

        JSONObject result = new JSONObject();
        JSONArray jsonRecipes = new JSONArray();
        for (Recipe r : recipes) {
            jsonRecipes.put(r.toJson());
        }
        return result.put("recipes", jsonRecipes);
    }
}
