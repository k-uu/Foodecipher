package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    Recipe recipe;
    private Ingredient i1, i2, i3;
    private List<Ingredient> ingredients;
    private NutritionFacts nfacts;

    private int[] nums;


    public static boolean equalIngredientList(List<Ingredient> l1, List<Ingredient> l2) {

        if (l1.size() != l2.size()) {
            return false;
        }
        for (int i = 0; i < l1.size(); i++) {
            Map<Nutrients, Ratio> i1 = l1.get(i).getNutrients();
            Map<Nutrients, Ratio> i2 = l2.get(i).getNutrients();

            if (!i1.equals(i2)) {
                return false;
            }
        }
        return true;
    }

    @BeforeEach
    void setup() {
        nums = new int[]{0, 2, 2, 3, 4, 5, 4, 3};

        i1 = new Ingredient("col0");
        i1.addNutrientRatio(Nutrients.CARBOHYDRATE, new Ratio(nums[0], 1));
        i1.addNutrientRatio(Nutrients.FIBRE, new Ratio(nums[1], 1));

        i2 = new Ingredient("col1");
        i2.addNutrientRatio(Nutrients.CARBOHYDRATE, new Ratio(nums[2], 1));
        i2.addNutrientRatio(Nutrients.FIBRE, new Ratio(nums[3], 1));

        i3 = new Ingredient("col2");
        i3.addNutrientRatio(Nutrients.CARBOHYDRATE, new Ratio(nums[4], 1));
        i3.addNutrientRatio(Nutrients.FIBRE, new Ratio(nums[5], 1));

        Map<Nutrients, Integer> nutrients = new HashMap<>();
        nutrients.put(Nutrients.CARBOHYDRATE, nums[6]);
        nutrients.put(Nutrients.FIBRE, nums[7]);

        nfacts = new NutritionFacts(1, nutrients);

        ingredients = new ArrayList<>();

    }

    @Test
    void testRecipe() {

        ingredients.add(i1);
        ingredients.add(i2);
        ingredients.add(i3);

        recipe = new Recipe("Bread", nfacts, ingredients);

        assertEquals("Bread", recipe.getName());

        assertTrue(equalIngredientList(ingredients, recipe.getIngredients()));

        assertTrue(nfacts.getFacts().equals(recipe.getNutritionFacts().getFacts()));

        assertEquals(3, recipe.getProportions().size());
    }

    @Test
    void solveMatrixTestSuccess() {
        double[][] input = {{5, 6, 4}, {2, 3, 5}, {1, 1, 1}};


        double[] constants = {4, 3, 1};

        List<Double> expected = new ArrayList<>();
        expected.add(1.0);
        expected.add(-0.5);
        expected.add(0.5);




        recipe = new Recipe("Bread", nfacts, ingredients);

        recipe.solveMatrix(input, constants);

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), recipe.getProportions().get(i), 0.01);
        }
    }

    @Test
    void solveMatrixTestFail() {
        double[][] input = {{4, 4, 4}, {2, 3, 5}, {1, 1, 1}};

        double[] constants = {4, 3, 1};

        recipe = new Recipe("Bread", nfacts, ingredients);

        recipe.solveMatrix(input, constants);

        assertEquals(0, recipe.getProportions().size());
    }

    @Test
    void findProportionsTestTrue() {

        ingredients.add(i1);
        ingredients.add(i2);
        ingredients.add(i3);

        recipe = new Recipe("Bread", nfacts, ingredients);

        try {
            recipe.findProportions();

        } catch (IllegalArgumentException e) {
            fail("correct arguments");
        }

        List<Double> result = recipe.getProportions();

        assertEquals(-2.0, result.get(0), 0.01);
        assertEquals(4.0, result.get(1), 0.01);
        assertEquals(-1.0, result.get(2), 0.01);
    }

    @Test
    void findProportionsTestFalse() {

        i1.changeNutrientRatio(Nutrients.CARBOHYDRATE, new Ratio(4, 1));
        i2.changeNutrientRatio(Nutrients.CARBOHYDRATE, new Ratio(4, 1));

        ingredients.add(i1);
        ingredients.add(i2);
        ingredients.add(i3);

        recipe = new Recipe("Bread", nfacts, ingredients);

        try {
            recipe.findProportions();
            assertEquals(0, recipe.getProportions().size());
        } catch (IllegalArgumentException e) {
            fail("order was correct");
        }

    }

    @Test
    void toStringTest() {

        ingredients.add(i1);
        ingredients.add(i2);
        ingredients.add(i3);

        recipe = new Recipe("Bread", nfacts, ingredients);

        recipe.findProportions();
        List<Double> prop = recipe.getProportions();
        DecimalFormat twoDecimals = new DecimalFormat("#.00");

        String expected = "* " + recipe.getName() + " *" + System.lineSeparator() +
                i1.getName() + ", Proportion: " + twoDecimals.format(prop.get(0)) + System.lineSeparator() +
                i2.getName() + ", Proportion: " + twoDecimals.format(prop.get(1)) + System.lineSeparator() +
                i3.getName() + ", Proportion: " + twoDecimals.format(prop.get(2));

        assertEquals(expected, recipe.toString());

    }

    @Test
    void toJsonTest() {

        ingredients.add(i1);
        ingredients.add(i2);
        ingredients.add(i3);

        recipe = new Recipe("Bread", nfacts, ingredients);
        List<Double> expected = recipe.getProportions();

        JSONObject jsonRecipe = recipe.toJson();
        assertEquals("Bread", jsonRecipe.get("name"));

        JSONObject nutrition = jsonRecipe.getJSONObject("nutrition");

        assertEquals(1, nutrition.get("servingSize"));

        JSONObject facts = nutrition.getJSONObject("facts");
        assertEquals(nums[6], facts.getJSONObject("CARBOHYDRATE").get("numerator"));
        assertEquals(1, facts.getJSONObject("CARBOHYDRATE").get("denominator"));

        assertEquals(nums[7], facts.getJSONObject("FIBRE").get("numerator"));
        assertEquals(1, facts.getJSONObject("FIBRE").get("denominator"));

        JSONArray ingredients = jsonRecipe.getJSONArray("ingredients");
        JSONArray proportions = jsonRecipe.getJSONArray("proportions");

        String[] names = {"col0", "col1", "col2"};
        int n2 = 0;

        for (int n = 0; n < ingredients.length(); n++) {
            JSONObject i = ingredients.getJSONObject(n);
            assertEquals(expected.get(n), proportions.get(n));
            JSONObject nutrients  = i.getJSONObject("nutrients");
            assertEquals(names[n], i.get("name"));
            assertEquals(nums[n2++], nutrients.getJSONObject("CARBOHYDRATE").get("numerator"));
            assertEquals(1, nutrients.getJSONObject("CARBOHYDRATE").get("denominator"));
            assertEquals(nums[n2++], nutrients.getJSONObject("FIBRE").get("numerator"));
            assertEquals(1, nutrients.getJSONObject("FIBRE").get("denominator"));
        }
    }
}
