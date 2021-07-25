package model.equations;

import model.Ratio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    Matrix m1;
    Ratio r1, r2, r3;
    List<Ratio> ratios;

    @BeforeEach
    public void setup() {

        r1 = new Ratio(6, 10);
        r2 = new Ratio(5, 10);
        r3 = new Ratio(3, 10);
        ratios = new ArrayList<>();
    }

    @Test
    public void testMatrixSmall() {

        m1 = new Matrix(3);
        assertEquals(4, m1.getColumnCount());
        assertEquals(3, m1.getRowCount());
    }

    @Test
    public void testMatrixLarge() {

        m1 = new Matrix(20);
        assertEquals(21, m1.getColumnCount());
        assertEquals(20, m1.getRowCount());
    }

    @Test
    public void testMatrixZero() {

        m1 = new Matrix(0);
        assertEquals(1, m1.getColumnCount());
        assertEquals(1, m1.getRowCount());
    }

    @Test
    public void testMatrixNeg() {

        m1 = new Matrix(-1);
        assertEquals(1, m1.getColumnCount());
        assertEquals(1, m1.getRowCount());
    }

    @Test
    public void setColumnTestZeroIndex() {

        m1 = new Matrix(3);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);

        m1.setColumn(ratios, 0);
        assertTrue(equalList(ratios, m1.getColumn(0)));

        ratios.set(0, r3);
        assertFalse(equalList(ratios, m1.getColumn(0)));

        m1.setColumn(ratios, 0);
        assertTrue(equalList(ratios, m1.getColumn(0)));
    }

    @Test
    public void setColumnTestMaxIndex() {

        m1 = new Matrix(3);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);

        m1.setColumn(ratios, 3);
        assertTrue(equalList(ratios, m1.getColumn(3)));

        ratios.set(2, r1);
        assertFalse(equalList(ratios, m1.getColumn(3)));

        m1.setColumn(ratios, 3);
        assertTrue(equalList(ratios, m1.getColumn(3)));
    }

    @Test
    public void setRowTestZeroIndex() {

        m1 = new Matrix(3);

        Ratio r4 = new Ratio(1, 2);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);
        ratios.add(r4);


        m1.setRow(ratios, 0);
        assertTrue(equalList(ratios, m1.getRow(0)));

        ratios.set(0, r2);
        assertFalse(equalList(ratios, m1.getRow(0)));

        m1.setRow(ratios, 0);
        assertTrue(equalList(ratios, m1.getRow(0)));


    }

    @Test
    public void setRowTestMaxIndex() {

        m1 = new Matrix(3);
        Ratio r4 = new Ratio(1, 2);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);
        ratios.add(r4);

        m1.setRow(ratios, 2);
        assertTrue(equalList(ratios, m1.getRow(2)));

        ratios.set(0, r2);
        assertFalse(equalList(ratios, m1.getRow(2)));

        m1.setRow(ratios, 2);
        assertTrue(equalList(ratios, m1.getRow(2)));



    }

    @Test
    public void getColumnTestZeroIndex() {

        setColumnTestZeroIndex();

        assertTrue(equalList(ratios, m1.getColumn(0)));
    }

    @Test
    public void getColumnTestMaxIndex() {

        setColumnTestMaxIndex();

        assertTrue(equalList(ratios, m1.getColumn(3)));
    }

    @Test
    public void getRowTestZeroIndex() {

        m1 = new Matrix(3);

        List<Ratio> expected = new ArrayList();

        expected.add(r1);
        expected.add(r1);
        expected.add(r1);
        expected.add(r1);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);

        m1.setColumn(ratios, 0);
        m1.setColumn(ratios, 1);
        m1.setColumn(ratios, 2);
        m1.setColumn(ratios, 3);

        assertTrue(equalList(expected, m1.getRow(0)));
    }

    @Test
    public void getRowTestMaxIndex() {

        m1 = new Matrix(3);

        List<Ratio> expected = new ArrayList();

        expected.add(r3);
        expected.add(r3);
        expected.add(r3);
        expected.add(r3);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);

        m1.setColumn(ratios, 0);
        m1.setColumn(ratios, 1);
        m1.setColumn(ratios, 2);
        m1.setColumn(ratios, 3);

        assertTrue(equalList(expected, m1.getRow(2)));
    }

    @Test
    public void copyTest() {

        m1 = new Matrix(3);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);

        m1.setColumn(ratios, 0);
        m1.setColumn(ratios, 1);
        m1.setColumn(ratios, 2);
        m1.setColumn(ratios, 3);

        Matrix copy = m1.copy();

        for (int i = 0; i <= 3; i++) {
            assertTrue(equalList(ratios, copy.getColumn(i)));
        }
    }

    public static boolean equalList(List<Ratio> l1, List<Ratio> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i).getNumerator() != l2.get(i).getNumerator() ||
                    l1.get(i).getDenominator() != l2.get(i).getDenominator()) {
                return false;
            }
        }
        return true;
    }
}