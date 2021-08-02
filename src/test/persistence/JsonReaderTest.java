package persistence;

import model.NutritionFacts;
import model.RecipeList;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/missing.json");
        try {
            RecipeList r = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // success
        }
    }

    @Test
    void testReaderEmptyRecipeList() {
        JsonReader reader = new JsonReader("./data/readEmptyRecipeList.json");
        try {
            reader.read();
        } catch (IOException e) {
            fail("Should have read file");
        }
    }

    @Test
    void testReaderGenericRecipeList() {
        JsonReader reader = new JsonReader("./data/readGenericRecipeList.json");
        try {
            reader.read();
        } catch (IOException e) {
            fail("Should have read file");
        }
    }
}
