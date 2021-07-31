package model;

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
        assertEquals(3, m1.getColumnCount());
        assertEquals(3, m1.getRowCount());
    }

    @Test
    public void testMatrixLarge() {

        m1 = new Matrix(20);
        assertEquals(20, m1.getColumnCount());
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
    public void setRowTestZeroIndex() {

        m1 = new Matrix(3);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);

        m1.setRow(ratios, 0);
        assertTrue(ratios.equals(m1.getRow(0)));

        ratios.set(0, r2);
        assertFalse(ratios.equals(m1.getRow(0)));

        m1.setRow(ratios, 0);
        assertTrue(ratios.equals(m1.getRow(0)));


    }

    @Test
    public void setRowTestMaxIndex() {

        m1 = new Matrix(3);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);

        m1.setRow(ratios, 2);
        assertTrue(ratios.equals(m1.getRow(2)));

        ratios.set(0, r2);
        assertFalse(ratios.equals(m1.getRow(2)));

        m1.setRow(ratios, 2);
        assertTrue(ratios.equals(m1.getRow(2)));



    }

    @Test
    public void getRowTestZeroIndex() {

        m1 = new Matrix(3);

        List<Ratio> expected = new ArrayList();

        expected.add(r1);
        expected.add(r2);
        expected.add(r2);

        List<Ratio> dif = new ArrayList<>();
        dif.add(r1);
        dif.add(r2);
        dif.add(r2);

        ratios.add(r3);
        ratios.add(r3);
        ratios.add(r3);

        m1.setRow(dif, 0);
        m1.setRow(ratios, 1);
        m1.setRow(ratios, 2);

        assertTrue(expected.equals(m1.getRow(0)));
    }

    @Test
    public void getRowTestMaxIndex() {

        m1 = new Matrix(3);

        List<Ratio> expected = new ArrayList();

        expected.add(r1);
        expected.add(r2);
        expected.add(r2);

        ratios.add(r3);
        ratios.add(r3);
        ratios.add(r3);

        List<Ratio> dif = new ArrayList<>();
        dif.add(r1);
        dif.add(r2);
        dif.add(r2);

        m1.setRow(ratios, 0);
        m1.setRow(ratios, 1);
        m1.setRow(dif, 2);

        assertTrue(expected.equals(m1.getRow(2)));
    }

    @Test
    public void getMatrixTest() {

        m1 = new Matrix(3);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);

        m1.setRow(ratios, 0);
        m1.setRow(ratios, 1);
        m1.setRow(ratios, 2);

        double[][] expected = {{0.6, 0.5, 0.3}, {0.6, 0.5, 0.3}, {0.6, 0.5, 0.3}};

        double[][] actual = m1.getMatrix();

        for (int row = 0; row < m1.getRowCount(); row++) {
            for (int colm = 0; colm < m1.getColumnCount(); colm++) {
               assertEquals(expected[row][colm], actual[row][colm]);
            }
        }
    }
}