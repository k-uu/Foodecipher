package model;

import org.apache.commons.math3.linear.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Represents a food recipe consisting of ingredients and their respective quantities
public class Recipe {

    private String name;
    private NutritionFacts facts;
    private List<Ingredient> ingredients;
    private List<Double> proportions;

    // REQUIRES: ingredients and nutritionFacts have n core nutrients and there are n + 1 elements in ingredients
    // EFFECTS: create a named recipe with Nutrition Facts and a list of ingredients with respective proportions
    public Recipe(String recipeName, NutritionFacts nutritionFacts, List<Ingredient> ingredients) {

        this.name = recipeName;
        this.facts = nutritionFacts;
        this.ingredients = new ArrayList<Ingredient>(ingredients);
        this.proportions = new ArrayList<>();
    }

//    // MODIFIES: this
//    // EFFECTS: adds a unique ingredient to the recipe and returns true. If List of ingredients
//    public boolean addIngredient(Ingredient ingredient) {}
//
//    // MODIFIES: this
//    // EFFECTS: removes an existing ingredient from the recipe and returns true, else returns false
//    public boolean removeIngredient(Ingredient ingredient) {
//        return false; //stub
//    }

    // EFFECTS: 

    // MODIFIES: this
    // EFFECTS: approximates the proportion of each ingredient in the recipe based on their individual
    // nutrient / mass ratios. If the approximation finds a unique solution update this and return true,
    // else return false. If a Nutrient is not in givenOrder, throw IllegalArgumentException
    public boolean findProportions(List<Nutrients> givenOrder) throws IllegalArgumentException {

        Matrix m = new Matrix(ingredients.size());
        List<Ratio> row = new ArrayList<>();
        double[] c = new double[givenOrder.size()];
        Map<Nutrients, Ratio> nf = facts.getFacts();
        List<Ratio> botRow = new ArrayList<>();
        int count = 0;

        for (Nutrients n : givenOrder) {
            row.clear();
            for (int i = 0; i < ingredients.size(); i++) {
                if (!ingredients.get(i).getNutrients().containsKey(n)) {
                    throw new IllegalArgumentException("Nutrient from givenOrder is not in ingredients");
                }
                row.add(ingredients.get(i).getNutrients().get(n));
            }
            row.add(facts.getFacts().get(n));
            m.setRow(row, count);
            c[count] = nf.get(n).getValue();
            count++;
            botRow.add(new Ratio(1,1));
        }
        m.setRow(botRow, m.getRowCount() - 1);

        RealMatrix coefficients = new Array2DRowRealMatrix(m.getMatrix());

        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();

        RealVector constants = new ArrayRealVector(c);

        if (!solver.isNonSingular()) {
            return false;
        }
        RealVector solution = solver.solve(constants);
        for (int i = 0; i < m.getColumnCount(); i++) {
            proportions.add((Double) solution.getEntry(i));
        }
        return true;
    }

    // EFFECTS: returns a String for the recipe with the given format:
    // * recipeName *
    // ingredientName, Proportion: proportion
    // ...
    @Override
    public String toString() {
        return ""; //stub
    }

    // getters
    public String getName() {
        return name;
    }

    public List<Double> getProportions() {
        return proportions;
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public NutritionFacts getNutritionFacts() {
        return this.facts;
    }

}
