package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    private Recipe recipe;
    private Ingredient i1, i2, i3;
    private List<Ingredient> ingredients;
    private NutritionFacts nfacts;
    private List<Nutrients> order;
    private int[] nums;

    @BeforeEach
    public void setup() {
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

        order = new ArrayList<>();
        order.add(Nutrients.CARBOHYDRATE);
        order.add(Nutrients.FIBRE);


    }

    @Test
    public void testRecipe() {

        ingredients.add(i1);
        ingredients.add(i2);
        ingredients.add(i3);

        recipe = new Recipe("Bread", nfacts, ingredients);

        assertEquals("Bread", recipe.getName());

        assertTrue(equalIngredientList(ingredients, recipe.getIngredients()));

        assertTrue(equalMap(nfacts.getFacts(), recipe.getNutritionFacts().getFacts()));

        assertEquals(0, recipe.getProportions().size());
    }

    @Test
    public void findProportionsTestTrue() {

        recipe = new Recipe("Bread", nfacts, ingredients);

        try {
            assertTrue(recipe.findProportions(order));
        } catch (IllegalArgumentException e) {
            fail("correct arguments");
        }

        List<Double> result = recipe.getProportions();

        assertEquals(-2.0, result.get(0),0.05);
        assertEquals(4.0, result.get(1), 0.05);
        assertEquals(-1.0, result.get(2), 0.05);
    }

    @Test
    public void findProportionsTestFalse() {

        i1.changeNutrientRatio(Nutrients.CARBOHYDRATE, new Ratio(4, 1));
        i2.changeNutrientRatio(Nutrients.CARBOHYDRATE, new Ratio(4, 1));

        recipe = new Recipe("Bread", nfacts, ingredients);

        try {
            assertFalse(recipe.findProportions(order));
        } catch (IllegalArgumentException e) {
            fail("order was correct");
        }

    }

    @Test
    public void findProportionsTestException() {

        order.set(0, Nutrients.POTASSIUM);

        recipe = new Recipe("Bread", nfacts, ingredients);

        try {
            assertTrue(recipe.findProportions(order));
            fail("POTASSIUM is not listed in ingredients");
        } catch (IllegalArgumentException e) {
            // success
        }
     }

     @Test
     public void findProportionsTestExceptionFail() {

         i1.changeNutrientRatio(Nutrients.CARBOHYDRATE, new Ratio(4, 1));
         i2.changeNutrientRatio(Nutrients.CARBOHYDRATE, new Ratio(4, 1));
         order.set(0, Nutrients.POTASSIUM);

         recipe = new Recipe("Bread", nfacts, ingredients);

         try {
             assertFalse(recipe.findProportions(order));
             fail("POTASSIUM is not listed in ingredients");
         } catch (IllegalArgumentException e) {
             // success
         }

     }

    public static boolean equalIngredientList(List<Ingredient> l1, List<Ingredient> l2) {

        if (l1.size() != l2.size()) {
            return false;
        }
        for (int i = 0; i < l1.size(); i++) {
            Map<Nutrients, Ratio> i1 = l1.get(i).getNutrients();
            Map<Nutrients, Ratio> i2 = l2.get(i).getNutrients();

            if (!equalMap(i1, i2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalMap(Map<Nutrients, Ratio> m1, Map<Nutrients, Ratio> m2) {

        if (m1.size() != m2.size()) {
            return false;
        }

        Set<Nutrients> nutrients = m1.keySet();

        for (int i = 0; i < m1.size(); i++) {
            for (Nutrients n : nutrients) {
                if (m2.containsKey(n)) {
                    if (m2.get(n).getNumerator() != m1.get(n).getNumerator() ||
                            m2.get(n).getDenominator() != m1.get(n).getDenominator()) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }




}
