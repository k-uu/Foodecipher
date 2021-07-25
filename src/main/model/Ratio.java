package model;

// Represents an immutable non-imaginary ratio / rational number and some operations that can be applied on it
public class Ratio {

    private int numerator;
    private int denominator;


    // EFFECTS: instantiates a valid ratio using given numerator and non-zero denominator If denominator is zero
    // throws a DivideByZeroException
    public Ratio(int numerator, int denominator) throws IllegalArgumentException {

        if (denominator == 0) {
            throw new IllegalArgumentException();
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // MODIFIES: this
    // EFFECTS: adds r to this
    public void add(Ratio r) {
        if (this.numerator == 0) {
            this.numerator = r.getNumerator();
            this.denominator = r.getDenominator();

        } else if (r.getNumerator() == 0) {
            // do nothing
        } else {
            this.numerator = (this.numerator * r.getDenominator()) + (r.getNumerator() * this.denominator);
            this.denominator = this.denominator * r.getDenominator();
        }
    }

    // MODIFIES: this
    // EFFECTS: subtracts r from this
    public void subtract(Ratio r) {

        if (this.numerator == 0) {
            this.numerator = r.getNumerator() * -1;
            this.denominator = r.getDenominator();
        } else if (r.getNumerator() == 0) {
            // do nothing
        } else {
            this.numerator = (this.numerator * r.getDenominator()) - (r.getNumerator() * this.denominator);
            this.denominator = this.denominator * r.getDenominator();
        }
    }

    // MODIFIES: this
    // EFFECTS: multiplies this by r. If r is 0 return 0 / 1
    public void multiply(Ratio r) {
        if (r.getNumerator() == 0) {
            this.numerator = 0;
            this.denominator = 1;
        } else {
            this.numerator *= r.getNumerator();
            this.denominator *= r.getDenominator();
        }

    }

    // MODIFIES: this
    // EFFECTS: multiplies this by n. If n is 0 return 0 / 1
    public void multiply(int n) {
        if (n == 0) {
            this.numerator = 0;
            this.denominator = 1;
        } else {
            this.numerator *= n;
        }
    }

    //getters:

    public int getNumerator() {

        return this.numerator;
    }

    public int getDenominator() {

        return this.denominator;
    }

    public double getValue() {
        return ((double) this.numerator) / this.denominator;
    }
}
