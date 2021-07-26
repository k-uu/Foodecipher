package model;

import java.util.ArrayList;
import java.util.List;

// Represents a square matrix of a defined size that can contain double as elements
public class Matrix {

    private Ratio[][] matrix;

    private final int rows;
    private final int columns;

    // EFFECTS: an empty matrix with n rows and n + 1 columns where the n + 1th column is augmented.
    // Returns a 1 x 1 matrix if n <= 0
    public Matrix(int n) {
        if (n <= 0) {
            matrix = new Ratio[1][1];
            rows = 1;
            columns = 1;
        } else {
            matrix = new Ratio[n][n];
            rows = n;
            columns = n;
        }
    }

    // REQUIRES: index is < number of rows, index >= 0 and that list contains the
    // same number of elements as the number of columns
    // MODIFIES: this
    // EFFECTS: set each element of the list, starting with the first, to elements in the array from the left
    // to right.
    public void setRow(List<Ratio> list, int index) {

        List<Ratio> copy = new ArrayList<>(list);
        for (int i = 0; i < columns; i++) {
            matrix[index][i] = copy.get(i);
        }
    }

    // REQUIRES: index is < number of rows and index >= 0
    // EFFECTS: returns a list containing elements in a row ordered left to right.
    public List<Ratio> getRow(int index) {

        List<Ratio> result = new ArrayList<>(columns);

        for (int i = 0; i < columns; i++) {
            Ratio r = matrix[index][i];
            result.add(new Ratio(r.getNumerator(), r.getDenominator()));
        }
        return result;
    }

    // getters
    public int getRowCount() {
        return rows;
    }

    public int getColumnCount() {
        return columns;
    }

    public double[][] getMatrix() {

        double[][] m = new double[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int colm = 0; colm < columns; colm++) {
                m[row][colm] = matrix[row][colm].getValue();
            }
        }
        return m;
    }
}
