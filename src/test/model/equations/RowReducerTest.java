package model.equations;

import model.Ratio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.equations.MatrixTest.equalList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RowReducerTest {

    RowReducer rr;
    Matrix m;
    Ratio r1, r2, r3;
    List<Ratio> ratios;

    @BeforeEach
    public void setup() {

        m = new Matrix(3);

        ratios = new ArrayList<>(3);

        r1 = new Ratio(6, 10);
        r2 = new Ratio(3, 10);
        r3 = new Ratio(7, 10);


        ratios.add(r1);
        ratios.add(r2);
        ratios.add(r3);

        m.setColumn(ratios, 0);
        m.setColumn(ratios, 1);
        m.setColumn(ratios, 2);
        m.setColumn(ratios, 3);

        rr = new RowReducer(m);

    }

    @Test
    public void testRowReducer() {

        Matrix current = rr.getMatrix();

        for (int i = 0; i <= 3; i++) {
            assertTrue(equalList(ratios, current.getColumn(i)));
        }
    }

    @Test
    public void swapRowsTest() {

        List<Ratio> expected = new ArrayList<>(3);

        expected.add(r3);
        expected.add(r2);
        expected.add(r1);

        rr.swapRows(0, 2);

        Matrix current = rr.getMatrix();

        for (int i = 0; i <= 3; i++) {
            assertTrue(equalList(expected, current.getColumn(i)));
        }
    }

    @Test
    public void scaleRowTestZeroIndex() {

        List<Ratio> scaled = new ArrayList<>(3);

        r1.multiply(3);

        scaled.add(r1);
        scaled.add(r2);
        scaled.add(r3);

        rr.scaleRow(0, 3);

        Matrix actual = rr.getMatrix();

        for (int i = 0; i <= 3; i++) {
            assertTrue(equalList(scaled, actual.getColumn(i)));
        }
    }

    @Test
    public void scaleRowTestMaxIndex() {

        List<Ratio> scaled = new ArrayList<>(3);

        r3.multiply(3);

        scaled.add(r1);
        scaled.add(r2);
        scaled.add(r3);

        rr.scaleRow(2, 3);

        Matrix actual = rr.getMatrix();

        for (int i = 0; i <= 3; i++) {
            assertTrue(equalList(scaled, actual.getColumn(i)));
        }
    }

    @Test
    public void replaceRowTestZeroFromMaxIndex() {

        List<Ratio> expected = new ArrayList<>(3);

        Ratio r4 = new Ratio(-10, 100);

        expected.add(r4);
        expected.add(r2);
        expected.add(r3);

        rr.replaceRow(0, 2, -1);

        for (int i = 0; i <= 3; i++) {
            assertTrue(equalList(expected, rr.getMatrix().getColumn(i)));
        }
    }

    @Test
    public void replaceRowTestMaxFromZeroIndex() {

        List<Ratio> expected = new ArrayList<>(3);

        Ratio r4 = new Ratio(19, 10);

        expected.add(r1);
        expected.add(r2);
        expected.add(r4);

        rr.replaceRow(2, 0, 2);

        for (int i = 0; i <= 3; i++) {
            assertTrue(equalList(expected, rr.getMatrix().getColumn(i)));
        }
    }

    @Test
    public void findSolutionTest() {

        List<Ratio> ratios = new ArrayList<>(12);

        for (int i = 0; i < 12; i++) {
            ratios.add(new Ratio(i, 1));
        }

        Matrix m = new Matrix(3);

        m.setColumn(ratios.subList(0, 3), 0);
        m.setColumn(ratios.subList(3, 6), 1);
        m.setColumn(ratios.subList(6, 9), 2);
        m.setColumn(ratios.subList(9, 12), 3);

        RowReducer reducer = new RowReducer(m);

        List<Double> solution = reducer.findSolution();

        assertEquals(-2.0, solution.get(0));
        assertEquals(3.0, solution.get(1));
        assertEquals(0.0, solution.get(2));
    }
}
