package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    Ingredient i1;
    Ratio r1, r2;
    Map<Nutrients, Ratio> map;

    @BeforeEach
    public void setup() {

        i1 = new Ingredient("Milk");
        r1 = new Ratio(2, 10);
        r2 = new Ratio(3, 8);
    }

    @Test
    public void testIngredient() {

        assertEquals("Milk", i1.getName());
        assertEquals(0, i1.getNutrients().size());
    }

    @Test
    public void addMultipleNutrientRatioTest() {

        i1.addNutrientRatio(Nutrients.CARBOHYDRATE, r1);
        i1.addNutrientRatio(Nutrients.SATURATED_FAT, r2);

        assertEquals(2, i1.getNutrients().size());
        assertEquals(r1, i1.getNutrients().get(Nutrients.CARBOHYDRATE));
        assertEquals(r2, i1.getNutrients().get(Nutrients.SATURATED_FAT));
    }

    @Test
    public void removeMultipleNutrientRatioTrue() {

        i1.addNutrientRatio(Nutrients.CARBOHYDRATE, r1);
        i1.addNutrientRatio(Nutrients.SATURATED_FAT, r2);

        assertTrue(i1.removeNutrientRatio(Nutrients.CARBOHYDRATE));
        assertEquals(1, i1.getNutrients().size());
        assertFalse(i1.getNutrients().containsKey(Nutrients.CARBOHYDRATE));

        assertTrue(i1.removeNutrientRatio(Nutrients.SATURATED_FAT));
        assertEquals(0, i1.getNutrients().size());
    }

    @Test
    public void removeNutrientRatioFalse() {

        assertFalse(i1.removeNutrientRatio(Nutrients.CARBOHYDRATE));

        i1.addNutrientRatio(Nutrients.CARBOHYDRATE, r1);
        i1.addNutrientRatio(Nutrients.SATURATED_FAT, r2);

        assertFalse(i1.removeNutrientRatio(Nutrients.PROTEIN));
        assertEquals(2, i1.getNutrients().size());
        assertTrue(i1.getNutrients().containsKey(Nutrients.CARBOHYDRATE));
        assertTrue(i1.getNutrients().containsKey(Nutrients.SATURATED_FAT));
    }

    @Test
    public void changeNutrientRatioTrue() {

        i1.addNutrientRatio(Nutrients.CARBOHYDRATE, r1);

        assertTrue(i1.changeNutrientRatio(Nutrients.CARBOHYDRATE, r2));

        assertEquals(1, i1.getNutrients().size());
        assertEquals(r2, i1.getNutrients().get(Nutrients.CARBOHYDRATE));
    }

    @Test
    public void changeNutrientRatioFalse() {

        i1.addNutrientRatio(Nutrients.PROTEIN, r1);

        assertFalse(i1.changeNutrientRatio(Nutrients.CARBOHYDRATE, r2));

        assertEquals(1, i1.getNutrients().size());
        assertEquals(r1, i1.getNutrients().get(Nutrients.CARBOHYDRATE));
    }
}
