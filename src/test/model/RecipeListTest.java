package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeListTest {

    RecipeList rlist;
    Ingredient i4, i5, i6, i7;
    NutritionFacts n1, n2;
    Recipe r1, r2;


    @BeforeEach
    void setup() {
        rlist = new RecipeList();

        List<Ingredient> list1 = new ArrayList<>();
        i4 = new Ingredient("Corn");
        i4.addNutrientRatio(Nutrients.CARBOHYDRATE, new Ratio(2, 10));
        list1.add(i4);
        i5 = new Ingredient("Bacon");
        i5.addNutrientRatio(Nutrients.CARBOHYDRATE, new Ratio(0, 10));
        list1.add(i5);
        Map<Nutrients, Integer> facts1= new HashMap<>();
        facts1.put(Nutrients.CARBOHYDRATE, 5);
        n1 = new NutritionFacts(10, facts1);

        r1 = new Recipe("BaconCorn", n1, list1);

        List<Ingredient> list2 = new ArrayList<>();
        i6 = new Ingredient("Tomato");
        i6.addNutrientRatio(Nutrients.SUGARS, new Ratio(3, 10));
        list2.add(i6);
        i7 = new Ingredient("Lettuce");
        i7.addNutrientRatio(Nutrients.SUGARS, new Ratio(2, 10));
        list2.add(i7);
        Map<Nutrients, Integer> facts2= new HashMap<>();
        facts2.put(Nutrients.SUGARS, 5);
        n2 = new NutritionFacts(9, facts2);

        r2 = new Recipe("BLT minus B", n2, list2);

    }

    @Test
    void testRecipeList() {
        assertEquals(0, rlist.getRecipeCount());
    }

    @Test
    void addMultipleRecipeTest() {
        rlist.addRecipe(r1);
        rlist.addRecipe(r2);
        assertEquals("BaconCorn", rlist.getRecipes().get(0).getName());
        assertEquals("BLT minus B", rlist.getRecipes().get(1).getName());
        assertEquals(2, rlist.getRecipeCount());

    }

    @Test
    void removeRecipeTestEmpty() {

        assertFalse(rlist.removeRecipe(r1));
        assertEquals(0, rlist.getRecipeCount());

    }

    @Test
    void removeRecipeTestMissing() {
        rlist.addRecipe(r2);
        assertFalse(rlist.removeRecipe(r1));
        assertEquals(1, rlist.getRecipeCount());

    }

    @Test
    void removeRecipeTest() {
        rlist.addRecipe(r2);
        assertTrue(rlist.removeRecipe(r2));
        assertEquals(0, rlist.getRecipeCount());
    }

    @Test
    void toStringTest() {
        rlist.addRecipe(r1);
        rlist.addRecipe(r2);

        String expected = "* RECIPES *" + System.lineSeparator() +
                "BaconCorn" + System.lineSeparator() +
                "BLT minus B";

        assertEquals(expected, rlist.toString());
    }
}
