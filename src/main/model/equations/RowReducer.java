package model.equations;

import java.util.ArrayList;

// Applicable matrix row operations and algorithm (Gauss-Jordan Elimination) for converting to reduced row echelon form
public abstract class RowReducer {

    // REQUIRES: indices row1 and row2 are >= 0 and < number of rows in m
    // MODIFIES: m
    // EFFECTS: swaps row1 and row2
    public static Matrix swapRows(Matrix m, int row1, int row2) {
        return new Matrix(1); //stub
    }

    // REQUIRES: row index is >= 0 and < number of rows in m
    // MODIFIES: m
    // EFFECTS: scales row by scalar
    public static Matrix scaleRow(Matrix m, int row, int scalar) {
        return new Matrix(1); //stub
    }

    // REQUIRES: indices targetRow and replacementRow are >= 0 and < number of rows in m, target row is not the same as
    // the replacement.
    // MODIFIES: m
    // EFFECTS: adds replacementRow multiplied by scalar to targetRow and sets result to targetRow
    public Matrix replaceRow(Matrix m, int targetRow, int replacementRow, int scalar) {
        return new Matrix(1); //stub
    }

    // EFFECTS: finds the solution to an augmented matrix. The returned array list represents a column vector with n
    // elements, where n is the number of rows of the matrix. If there is no solution, return an empty ArrayList.
    public static ArrayList<Double> findSolution(Matrix m) {
        return new ArrayList<>();
    }

}
