package model;

import java.util.HashMap;
import java.util.Map;

// Represents a Nutrition Facts label with a serving size and core nutrient amounts
public class NutritionFacts {

    // EFFECTS: creates a map of nutrition facts for the given positive servingSize. If zero or a negative value
    // is given to serving size,
    // throw IllegalArgumentException
    public NutritionFacts(int servingSize, Map<Nutrients, Ratio> facts) throws IllegalArgumentException { }


//    // MODIFIES: this
//    // EFFECTS: adds a nutrient and its amount to this for the given servingSize
//    public void addNutritionFact(Nutrients nutrient, double amount) { }
//
//
//    // MODIFIES: this
//    // EFFECTS: removes an existing nutrient from this and returns true, else returns false
//    public boolean removeNutritionFact(Nutrients nutrient) {
//        return false; //stub
//    }

    // getters

    public Map<Nutrients, Ratio> getFacts() {
        return new HashMap<>(); //stub
    }

    public int getServingSize() {
        return 0; //stub
    }

}
