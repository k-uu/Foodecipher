package model;

import java.util.HashMap;
import java.util.Map;

// Represents a food item with its associated nutrition
public class Ingredient {

    // EFFECTS: an ingredient with given name and empty nutrient / mass ratio list;
    public Ingredient(String name) { }

    // REQUIRES: nutrient type added does not already exist
    // MODIFIES: this
    // EFFECTS: adds a nutrient / mass ratio to this
    public void addNutrientRatio(Nutrients nutrient, Ratio ratio) { }

    // MODIFIES: this
    // EFFECTS: removes an existing nutrient / mass ratio from this and returns true, else returns false
    public boolean removeNutrientRatio(Nutrients nutrient) {
        return false; //stub
    }

    // MODIFIES: this
    // EFFECTS: change the ratio of an existing nutrient and return true, else return false
    public boolean changeNutrientRatio(Nutrients nutrient, Ratio ratio) {
        return false; //stub
    }

    // getters
    public Map<Nutrients, Ratio> getNutrients() {
        return new HashMap<>();
    }

    public String getName() {
        return ""; //stub
    }



}
