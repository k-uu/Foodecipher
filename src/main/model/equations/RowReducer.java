package model.equations;

import model.Ratio;

import java.util.ArrayList;
import java.util.List;

// Applicable matrix row operations and algorithm (Gauss-Jordan Elimination) for converting to reduced row echelon form
public class RowReducer {

    private Matrix matrix;

    // REQUIRES: m does not have any empty elements
    // EFFECTS: stores initial matrix to find solution to
    public RowReducer(Matrix m) {

        matrix = m.copy();
    }

    // REQUIRES: indices row1 and row2 are >= 0 and < number of rows in m
    // MODIFIES: this
    // EFFECTS: swaps row1 and row2
    public void swapRows(int row1, int row2) {

        List<Ratio> temp = matrix.getRow(row1);

        matrix.setRow(matrix.getRow(row2), row1);

        matrix.setRow(temp, row2);

    }

    // REQUIRES: row index is >= 0 and < number of rows in m
    // MODIFIES: this
    // EFFECTS: scales row by scalar
    public void scaleRow(int row, int scalar) {

        List<Ratio> ratios = matrix.getRow(row);

        for (Ratio r : ratios) {
            r.multiply(scalar);
        }

        matrix.setRow(ratios, row);
    }

    // REQUIRES: indices targetRow and replacementRow are >= 0 and < number of rows in m
    // MODIFIES: this
    // EFFECTS: adds replacementRow multiplied by scalar to targetRow and sets result to targetRow
    public void replaceRow(int targetRow, int replacementRow, int scalar) {

        List<Ratio> replacement = matrix.getRow(replacementRow);

        List<Ratio> target = matrix.getRow(targetRow);

        for (int colm = 0; colm < matrix.getColumnCount(); colm++) {

            replacement.get(colm).multiply(scalar);

            target.get(colm).add(replacement.get(colm));
        }

        matrix.setRow(target, targetRow);

    }

    // EFFECTS: finds the solution to an augmented matrix. The returned array list represents a column vector with n
    // elements, where n is the number of rows of the matrix. If there is no unique solution, return an empty ArrayList.
    public ArrayList<Double> findSolution() {

        for (int row = 0; row < matrix.getRowCount(); row++) {

            List<Ratio> r = matrix.getRow(row);

            double rowSum = 0;

            for (int i = 0; i < matrix.getColumnCount(); i++) {
                rowSum += r.get(i).getValue();
            }

            if (rowSum == 0.0) {
                return new ArrayList<Double>();
            }
        }


    }

    //getters
    public Matrix getMatrix() {
        return matrix.copy();
    }



}
