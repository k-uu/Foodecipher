package model;

import org.json.JSONArray;
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
    void testNutritionFactsSucceed() {

        try {
            facts1 = new NutritionFacts(100, map);
        } catch (IllegalArgumentException e) {
            fail("unexpected exception");
        }

        assertEquals(100, facts1.getServingSize());
        assertTrue(expected.equals(facts1.getFacts()));
    }

    @Test
    void testNutritionFactsFailZero() {

        try {
            facts1 = new NutritionFacts(0, map);
            fail("divide by zero");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    void testNutritionFactsFailNeg() {

        try {
            facts1 = new NutritionFacts(-10, map);
            fail("negative serving size");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    void toJsonTest() {

        facts1 = new NutritionFacts(100, map);

        JSONObject result = facts1.toJson();

        assertEquals(100, result.get("servingSize"));

        JSONObject facts = result.getJSONObject("facts");

        JSONObject n1 = facts.getJSONObject("CARBOHYDRATE");
        assertEquals(a1, n1.get("numerator"));
        assertEquals(100, n1.get("denominator"));

        JSONObject n2 = facts.getJSONObject("FIBRE");
        assertEquals(a2, n2.get("numerator"));
        assertEquals(100, n2.get("denominator"));
    }
}
