package model.equations;

import model.Ratio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Represents a square augmented matrix of a defined size that can contain Ratio as elements
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
            matrix = new Ratio[n][n + 1];
            rows = n;
            columns = n + 1;
        }
    }

    // REQUIRES: index is < number of columns, index >= 0 and that list contains the
    // same number of elements as the number of rows
    // MODIFIES: this
    // EFFECTS: set each element of the list, starting with the first, to elements in the array from the top
    // to bottom.
    public void setColumn(List<Ratio> list, int index) {

        List<Ratio> copy = new ArrayList<>(list);
        for (int i = 0; i < rows; i++) {
            matrix[i][index] = copy.get(i);
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


    // REQUIRES: index is < number of columns and index >= 0
    // EFFECTS: returns a list containing elements in a column ordered top to bottom.
    public List<Ratio> getColumn(int index) {

        List<Ratio> result = new ArrayList<>(rows);

        for (int i = 0; i < rows; i++) {
            Ratio r = matrix[i][index];
            result.add(new Ratio(r.getNumerator(), r.getDenominator()));
        }
        return result;
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

    // REQUIRES: this does not contain empty elements
    // EFFECTS: returns a copy of the matrix
    public Matrix copy() {

        Matrix copy = new Matrix(rows);

        for (int colm = 0; colm < columns; colm++) {
            List<Ratio> r = new ArrayList<>(this.getColumn(colm));
            copy.setColumn(r, colm);
        }
        return copy;
    }

    // getters
    public int getRowCount() {
        return rows;
    }

    public int getColumnCount() {
        return columns;
    }
}
