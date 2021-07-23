package model;

// Represents an immutable non-imaginary ratio / rational number and some operations that can be applied on it
public class Ratio {


    // EFFECTS: instantiates a valid ratio using given numerator and non-zero denominator If denominator is zero
    // throws a DivideByZeroException
    public Ratio(int numerator, int denominator) throws IllegalArgumentException { }

    // MODIFIES: this
    // EFFECTS: adds r to this
    public void add(Ratio r) { }

    // MODIFIES: this
    // EFFECTS: subtracts r from this
    public void subtract(Ratio r) { }

    // MODIFIES: this
    // EFFECTS: multiplies this by r
    public void multiply(Ratio r) { }

    // MODIFIES: this
    // EFFECTS: multiplies this by n
    public void multiply(int n) { }

    //getters:

    public double getNumerator() {
        return 0.0; //stub
    }

    public double getDenominator() {
        return 0.0; //stub
    }

    public double getValue() {
        return 0.0; //stub
    }
}
