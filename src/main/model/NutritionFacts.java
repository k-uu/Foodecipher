package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;
import java.util.Map;

// Represents a Nutrition Facts label with a serving size and core nutrient amounts
public class NutritionFacts implements Writable {

    private int servingSize;
    private Map<Nutrients, Ratio> facts;

    // EFFECTS: creates a map of nutrition facts for the given positive servingSize. If zero or a negative value
    // is given to serving size, throw IllegalArgumentException
    public NutritionFacts(int servingSize, Map<Nutrients, Integer> facts) throws IllegalArgumentException {
        if (servingSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.servingSize = servingSize;

        this.facts = new HashMap<>();

        for (Nutrients n : facts.keySet()) {
            this.facts.put(n, new Ratio(facts.get(n), servingSize));
        }
    }


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
        return new HashMap<>(facts);
    }

    public int getServingSize() {
        return servingSize;
    }

    // EFFECTS: returns nutrition facts as a JSON object
    @Override
    public JSONObject toJson() {
        return null;
    }
}
