package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NutrientsTest {

    @Test
    public void getValueTest() {
        assertEquals("Carbohydrate", Nutrients.CARBOHYDRATE.getValue());
    }
}
