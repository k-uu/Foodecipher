package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NutritionFactsTest {

    NutritionFacts facts1;
    Ratio r1, r2;
    Map<Nutrients, Ratio> map;

    @BeforeEach
    public void setup() {
        r1 = new Ratio(8, 10);
        r2 = new Ratio(6, 10);

        map = new HashMap<>();

        map.put(Nutrients.CARBOHYDRATE, r1);
        map.put(Nutrients.FIBRE, r2);

    }

    @Test
    public void testNutritionFactsSucceed() {
        try {
            facts1 = new NutritionFacts(100, map);
        } catch (IllegalArgumentException e) {
            fail("unexpected exception");
        }

        assertEquals(100, facts1.getServingSize());
        assertEquals(map, facts1.getFacts());

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
