package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.EnumMap;
import java.util.Map;

// Represents a food item with its associated nutrition
public class Ingredient implements Writable {

    private String name;
    private Map<Nutrients, Ratio> nutrients;

    // EFFECTS: an ingredient with given name and empty nutrient / mass ratio list.
    public Ingredient(String name) {

        this.name = name;
        nutrients = new EnumMap<>(Nutrients.class);
    }

    // MODIFIES: this
    // EFFECTS: adds a nutrient / mass ratio to this
    public void addNutrientRatio(Nutrients nutrient, Ratio ratio) {

        nutrients.put(nutrient, ratio);
    }

    // MODIFIES: this
    // EFFECTS: removes an existing nutrient / mass ratio from this and returns true, else returns false
    public boolean removeNutrientRatio(Nutrients nutrient) {

        if (nutrients.containsKey(nutrient)) {
            nutrients.remove(nutrient);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: change the ratio of an existing nutrient and return true, else return false
    public boolean changeNutrientRatio(Nutrients nutrient, Ratio ratio) {

        return nutrients.replace(nutrient, nutrients.get(nutrient), ratio);
    }

    // getters
    public Map<Nutrients, Ratio> getNutrients() {
        return new EnumMap<>(nutrients);
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns ingredient as a JSON object
    @Override
    public JSONObject toJson() {

        JSONObject result = new JSONObject();
        JSONObject ratios = new JSONObject();

        result.put("name", name);

        for (Map.Entry<Nutrients, Ratio> n : nutrients.entrySet()) {
            ratios.put(n.getKey().name(), n.getValue().toJson());
        }
        result.put("nutrients", ratios);

        return result;
    }
}
