package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

// represents a JSON file reader for RecipeList. Inspired by JsonSerializationDemo:
// https://repo1.maven.org/maven2/org/json/json/20200518/json-20200518.jar
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from file given by path
    public JsonReader(String path) {
        this.source = path;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RecipeList read() throws IOException {

        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeList(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: returns a deserialized RecipeList
    private RecipeList parseRecipeList(JSONObject json) {

        RecipeList recipes = new RecipeList();
        JSONArray jsonRecipes = json.getJSONArray("recipes");
        for (int i = 0; i < jsonRecipes.length(); i++) {
            recipes.addRecipe(parseRecipe(jsonRecipes.getJSONObject(i)));
        }
        return recipes;
    }

    // EFFECTS: returns a deserialized Ratio object
    private Ratio parseRatio(JSONObject json) {

        return new Ratio(json.getInt("numerator"), json.getInt("denominator"));
    }

    // EFFECTS: returns a deserialized NutritionFacts object
    public NutritionFacts parseNutritionFacts(JSONObject json) {

        int size = json.getInt("servingSize");
        Map<Nutrients, Integer> facts = new EnumMap<>(Nutrients.class);
        JSONObject jfacts = json.getJSONObject("facts");
        for (Nutrients n: Nutrients.values()) {
            if (jfacts.has(n.name())) {
                facts.put(n, jfacts.getInt(n.name()));
            }
        }
        return new NutritionFacts(size, facts);
    }

    // EFFECTS: returns a deserialized Ingredient object
    public Ingredient parseIngredient(JSONObject json) {

        Ingredient i = new Ingredient(json.getString("name"));
        JSONObject nutrients = json.getJSONObject("nutrients");

        for (Nutrients n : Nutrients.values()) {
            if (nutrients.has(n.name())) {
                i.addNutrientRatio(n, parseRatio(nutrients.getJSONObject(n.name())));
            }
        }
        return i;
    }

    public Recipe parseRecipe(JSONObject json) {

        String name = json.getString("name");
        NutritionFacts nutrition = parseNutritionFacts(json.getJSONObject("nutrition"));
        List<Ingredient> ingredients = new ArrayList<>();
        JSONArray jsonIngredients = json.getJSONArray("ingredients");

        for (int i = 0; i < jsonIngredients.length(); i++) {
            ingredients.add(parseIngredient(jsonIngredients.getJSONObject(i)));
        }

        return new Recipe(name, nutrition, ingredients);
    }
}


