package model;

// Represents a non-imaginary ratio
public class Ratio {

    private final double numerator;
    private final double denominator;

    //EFFECTS: instantiates a valid ratio using given numerator and non-zero denominator
    public Ratio(double numerator, double denominator) {}

    //getters:

    public double getNumerator() {
        return numerator;
    }

    public double getDenominator() {
        return denominator;
    }
}
