package model.equations;

import model.Ratio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixTest {

    Matrix m1;
    Ratio r1, r2, r3;
    List<Ratio> ratios;

    @BeforeEach
    public void setup() {
        r1 = new Ratio(6, 10);
        r2 = new Ratio(5, 10);
        r3 = new Ratio(3, 10);
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
    public void setColumnTestAtZero() {
        m1 = new Matrix(3);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);

        m1.setColumn(ratios, 0);
        assertEquals(ratios, m1.getColumn(0));

        ratios.set(0, r3);

        m1.setColumn(ratios, 0);
        assertEquals(ratios, m1.getColumn(0));
    }

    @Test
    public void setColumnTestAtMax() {
        m1 = new Matrix(3);

        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);

        m1.setColumn(ratios, 3);
        assertEquals(ratios, m1.getColumn(3));

        ratios.set(3, r1);

        m1.setColumn(ratios, 3);
        assertEquals(ratios, m1.getColumn(3));
    }

    @Test
    public void getColumnTestAtZero() {

    }

    @Test
    public void getColumnTestAtMax() {

    }

    @Test
    public void getRowTestAtZero() {

    }

    @Test
    public void getRowTestAtMax() {

    }



}