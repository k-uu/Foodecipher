package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends RecipeTest{

    RecipeList recipes;

    @Test
    void testWriterInvalidFile() {
        try {
            RecipeList list = new RecipeList();
            JsonWriter writer = new JsonWriter("./data/m\0ssing.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // success
        }
    }

    @Test
    void testWriterEmptyRecipeList() {
        RecipeList list = new RecipeList();
        JsonWriter writer = new JsonWriter("./data/writeEmptyRecipeList.json");
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            fail("File should have been found");
        }
        writer.write(list);
        writer.close();

        JsonReader reader = new JsonReader("./data/writeEmptyRecipeList.json");
        try {
            list = reader.read();
            assertEquals(0, list.getRecipeCount());
        } catch (IOException e) {
           fail("The file should have been read");
        }
    }

    @Test
    void testWriterGenericRecipeList() {

        recipes = new RecipeList();

        Map<Nutrients, Integer> n1 = new EnumMap<Nutrients, Integer>(Nutrients.class);
        n1.put(Nutrients.PROTEIN, 8);

        NutritionFacts f1 = new NutritionFacts(10, n1);

        Ingredient flour = new Ingredient("Flour");
        flour.addNutrientRatio(Nutrients.PROTEIN, new Ratio(5, 100));
        Ingredient yeast = new Ingredient("Yeast");
        yeast.addNutrientRatio(Nutrients.PROTEIN, new Ratio(2, 10));

        List<Ingredient> ings = new ArrayList<>();
        ings.add(flour);
        ings.add(yeast);

        recipes.addRecipe(new Recipe("Bread", f1, ings));

        n1.put(Nutrients.PROTEIN, 4);
        n1.put(Nutrients.FIBRE, 3);

        NutritionFacts f2 = new NutritionFacts(1, n1);

        Ingredient apple = new Ingredient("Apple");
        apple.addNutrientRatio(Nutrients.PROTEIN, new Ratio(4, 1));
        apple.addNutrientRatio(Nutrients.FIBRE, new Ratio(5, 1));
        Ingredient cornstarch = new Ingredient("Cornstarch");
        cornstarch.addNutrientRatio(Nutrients.PROTEIN, new Ratio(3, 1));
        cornstarch.addNutrientRatio(Nutrients.FIBRE, new Ratio(6, 1));
        Ingredient sugar = new Ingredient("Sugar");
        sugar.addNutrientRatio(Nutrients.PROTEIN, new Ratio(2, 1));
        sugar.addNutrientRatio(Nutrients.FIBRE, new Ratio(0, 1));
        ings.set(0, apple);
        ings.set(1, cornstarch);
        ings.add(sugar);

        recipes.addRecipe(new Recipe("Pie", f2, ings));

        JsonWriter writer = new JsonWriter("./data/writeGenericRecipeList.json");
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            fail("File should have been found");
        }
        writer.write(recipes);
        writer.close();
        try {
            JsonReader tempReader = new JsonReader("");
            String expected = tempReader.readFile("./data/readGenericRecipeList.json");
            String actual = tempReader.readFile("./data/writeGenericRecipeList.json");
            JSONAssert.assertEquals(expected, actual, true);
        } catch (IOException i) {
            fail("Files should have been read");
        }
    }
}
