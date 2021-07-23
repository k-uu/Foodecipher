package model.equations;

import model.Ratio;

import java.util.LinkedList;
import java.util.List;

// Represents a square augmented matrix of a defined size
public class Matrix {

    // EFFECTS: an empty matrix with n rows and n + 1 columns where the n + 1th column is augmented.
    // Returns a 1 x 1 matrix if n <= 0
    public Matrix(int n) { }

    // REQUIRES: index is < number of columns, index >= 0 and that list contains the
    // same number of elements as the number of rows
    // MODIFIES: this
    // EFFECTS: set each element of the list, starting with the first, to elements in the array from the top
    // to bottom.
    public void setColumn(List<Ratio> list, int index) { }


    // REQUIRES: index is < number of columns and index >= 0
    // EFFECTS: returns a list containing elements in a column ordered top to bottom.
    public List<Ratio> getColumn(int index) {
        return new LinkedList<>(); //stub
    }

    // REQUIRES: index is < number of rows and index >= 0
    // EFFECTS: returns a list containing elements in a row ordered left to right.
    public List<Ratio> getRow(int index) {
        return new LinkedList<>(); //stub
    }

    // getters
    public int getRowCount() {
        return 0; //stub
    }

    public int getColumnCount() {
        return 0; //stub
    }
}
