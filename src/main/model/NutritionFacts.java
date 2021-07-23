package model;

import java.util.HashMap;
import java.util.Map;

public class NutritionFacts {

    // EFFECTS: creates an empty list of nutrition facts for the given servingSize
    public NutritionFacts(int servingSize) { }


    // MODIFIES: this
    // EFFECTS: adds a nutrient and its amount to this for the given servingSize
    public void addNutritionFact(Nutrients nutrient, double amount) { }


    // MODIFIES: this
    // EFFECTS: removes an existing nutrient from this and returns true, else returns false
    public boolean removeNutritionFact(Nutrients nutrient) {
        return false; //stub
    }

    // getters

    public Map<Nutrients, Ratio> getFacts() {
        return new HashMap<>(); //stub
    }

}
