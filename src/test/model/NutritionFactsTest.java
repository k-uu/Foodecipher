package model;


import org.json.JSONObject;
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
    void setup() {

        a1 = 8;
        a2 = 5;

        map = new HashMap<>();

        map.put(Nutrients.CARBOHYDRATE, a1);
        map.put(Nutrients.FIBRE, a2);

        expected = new HashMap<>();
        expected.put(Nutrients.CARBOHYDRATE, new Ratio(a1, 100));
        expected.put(Nutrients.FIBRE, new Ratio(a2, 100));
    }

    @Test
    void testNutritionFactsSucceedBorderCase() {

        expected.put(Nutrients.CARBOHYDRATE, new Ratio(a1, 1));
        expected.put(Nutrients.FIBRE, new Ratio(a2, 1));

        try {
            facts1 = new NutritionFacts(1, map);
        } catch (IllegalArgumentException e) {
            fail("unexpected exception");
        }

        assertEquals(1, facts1.getServingSize());
        assertEquals(expected, facts1.getFacts());
    }

    @Test
    void testNutritionFactsSucceed() {

        try {
            facts1 = new NutritionFacts(100, map);
        } catch (IllegalArgumentException e) {
            fail("unexpected exception");
        }

        assertEquals(100, facts1.getServingSize());
        assertEquals(expected, facts1.getFacts());
    }

    @Test
    void testNutritionFactsFailZero() {

        try {
            facts1 = new NutritionFacts(0, map);
            fail("divide by zero");
        } catch (IllegalArgumentException e) {
            // success
        }
    }

    @Test
    void testNutritionFactsFailNeg() {

        try {
            facts1 = new NutritionFacts(-10, map);
            fail("negative serving size");
        } catch (IllegalArgumentException e) {
            //success
        }
    }

    @Test
    void toJsonTest() {

        facts1 = new NutritionFacts(100, map);

        JSONObject result = facts1.toJson();

        assertEquals(100, result.get("servingSize"));

        JSONObject facts = result.getJSONObject("facts");

        assertEquals(a1, facts.getInt("CARBOHYDRATE"));

        assertEquals(a2, facts.getInt("FIBRE"));
    }
}
