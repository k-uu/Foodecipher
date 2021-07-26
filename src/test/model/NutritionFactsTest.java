package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NutritionFactsTest {

    NutritionFacts facts1;
    Integer a1, a2;
    Map<Nutrients, Integer> map;
    Map<Nutrients, Ratio> expected;

    @BeforeEach
    public void setup() {

        a1 = 8;
        a2 = 5;

        map = new HashMap<>();

        map.put(Nutrients.CARBOHYDRATE, a1);
        map.put(Nutrients.FIBRE, a2);

        expected = new HashMap<>();
        expected.put(Nutrients.CARBOHYDRATE, new Ratio(8, 100));
        expected.put(Nutrients.FIBRE, new Ratio(5, 100));
    }

    @Test
    public void testNutritionFactsSucceed() {

        try {
            facts1 = new NutritionFacts(100, map);
        } catch (IllegalArgumentException e) {
            fail("unexpected exception");
        }

        assertEquals(100, facts1.getServingSize());
        assertTrue(RecipeTest.equalMap(expected, facts1.getFacts()));
    }

    @Test
    public void testNutritionFactsFailZero() {

        try {
            facts1 = new NutritionFacts(0, map);
            fail("divide by zero");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testNutritionFactsFailNeg() {

        try {
            facts1 = new NutritionFacts(-10, map);
            fail("negative serving size");
        } catch (IllegalArgumentException e) {

        }
    }
}
