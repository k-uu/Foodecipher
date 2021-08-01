package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    Ingredient i1;
    Ratio r1, r2;
    List<Nutrients> order;

    @BeforeEach
    public void setup() {

        i1 = new Ingredient("Milk");
        r1 = new Ratio(2, 10);
        r2 = new Ratio(3, 8);
        order = new ArrayList<>();
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

        order.add(Nutrients.SATURATED_FAT);
        order.add(Nutrients.CARBOHYDRATE);

        assertEquals(2, i1.getNutrients().size());
        assertEquals(r1, i1.getNutrients().get(Nutrients.CARBOHYDRATE));
        assertEquals(r2, i1.getNutrients().get(Nutrients.SATURATED_FAT));

        Collection keys = i1.getNutrients().keySet();

        // confirm order of EnumMap
        assertEquals(order, new ArrayList<>(keys));
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

        order.add(Nutrients.SATURATED_FAT);
        order.add(Nutrients.CARBOHYDRATE);

        assertFalse(i1.removeNutrientRatio(Nutrients.PROTEIN));

        Collection keys = i1.getNutrients().keySet();
        assertEquals(order, new ArrayList<>(keys));

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
        assertEquals(r1, i1.getNutrients().get(Nutrients.PROTEIN));
    }
}
