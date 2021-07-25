package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RatioTest {

    Ratio r1, r2, r3, r4;

    @Test
    public void testRatioSucceed() {

        try {
            r1 = new Ratio(8, 10);
            r2 = new Ratio(4, 3);
            r3 = new Ratio(-1, 4);
            r4 = new Ratio(0 ,1);
        } catch (IllegalArgumentException e) {
            fail("unexpected exception");
        }
        assertEquals(8, r1.getNumerator());
        assertEquals(10, r1.getDenominator());
    }

    @Test
    public void testRatioFail() {

        try {
            r1 = new Ratio(10, 0);
            fail("divide by zero");
        } catch (IllegalArgumentException e) {
            //success!
        }
    }

    @Test
    public void addTest() {

        r1 = new Ratio(8, 10);
        r2 = new Ratio(4, 3);
        r3 = new Ratio(-1, 4);
        r4 = new Ratio(0 ,1);

        r1.add(r2);
        assertEquals(64, r1.getNumerator());
        assertEquals(30, r1.getDenominator());

        r1.add(r3);
        assertEquals(226, r1.getNumerator());
        assertEquals(120, r1.getDenominator());

        r1.add(r4);
        assertEquals(226, r1.getNumerator());
        assertEquals(120, r1.getDenominator());

        checkInputs();

        r4.add(r3);
        assertEquals(r3.getNumerator(), r4.getNumerator());
        assertEquals(r3.getDenominator(), r4.getDenominator());

        assertEquals(-1, r3.getNumerator());
        assertEquals(4, r3.getDenominator());
    }

    @Test
    public void subtractTest() {

        r1 = new Ratio(8, 10);
        r2 = new Ratio(4, 3);
        r3 = new Ratio(-1, 4);
        r4 = new Ratio(0 ,1);

        r1.subtract(r3);
        assertEquals(42, r1.getNumerator());
        assertEquals(40, r1.getDenominator());

        r1.subtract(r2);
        assertEquals(-34, r1.getNumerator());
        assertEquals(120, r1.getDenominator());

        r1.subtract(r4);
        assertEquals(-34, r1.getNumerator());
        assertEquals(120, r1.getDenominator());

        checkInputs();

        r4.subtract(r2);
        assertEquals(-1 * r2.getNumerator(), r4.getNumerator());
        assertEquals(r2.getDenominator(), r4.getDenominator());

        assertEquals(4, r2.getNumerator());
        assertEquals(3, r2.getDenominator());
    }

    @Test
    public void multiplyTest() {

        r1 = new Ratio(8, 10);
        r2 = new Ratio(4, 3);
        r3 = new Ratio(-1, 4);
        r4 = new Ratio(0 ,1);

        r1.multiply(3);
        assertEquals(24, r1.getNumerator());
        assertEquals(10, r1.getDenominator());

        r1.multiply(r2);
        assertEquals(96, r1.getNumerator());
        assertEquals(30, r1.getDenominator());

        r1.multiply(-1);
        assertEquals(-96, r1.getNumerator());
        assertEquals(30, r1.getDenominator());

        r1.multiply(r3);
        assertEquals(96, r1.getNumerator());
        assertEquals(120, r1.getDenominator());

        r1.multiply(r4);
        assertEquals(0, r1.getNumerator());
        assertEquals(1, r1.getDenominator());

        r1.multiply(0);
        assertEquals(0, r1.getNumerator());
        assertEquals(1, r1.getDenominator());

        checkInputs();
    }

    @Test
    public void getValueTest() {

        r1 = new Ratio(8, 10);
        assertEquals(0.8, r1.getValue(), 0.05);
    }

    private void checkInputs() {

        assertEquals(4, r2.getNumerator());
        assertEquals(3, r2.getDenominator());

        assertEquals(-1, r3.getNumerator());
        assertEquals(4, r3.getDenominator());

        assertEquals(0, r4.getNumerator());
        assertEquals(1, r4.getDenominator());
    }
}
