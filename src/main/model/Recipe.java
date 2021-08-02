package model;

import org.apache.commons.math3.linear.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


// Represents a food recipe consisting of ingredients and their respective quantities
public class Recipe implements Writable {

    private NutritionFacts facts;
    private List<Ingredient> ingredients;

    private List<Double> proportions;
    private String name;

    // REQUIRES: ingredients and nutritionFacts have n core nutrients and there are n + 1 elements in ingredients
    // EFFECTS: create a named recipe with Nutrition Facts and a list of ingredients with respective proportions
    public Recipe(String recipeName, NutritionFacts nutritionFacts, List<Ingredient> ingredients) {

        this.name = recipeName;
        this.facts = nutritionFacts;
        this.ingredients = new ArrayList<>(ingredients);
        this.proportions = new ArrayList<>(ingredients.size());
        findProportions();
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

    // MODIFIES: this
    // EFFECTS: approximates the proportion of each ingredient in the recipe based on their individual
    // nutrient / mass ratios.
    public void findProportions() {

        Matrix m = new Matrix(ingredients.size());
        double[] augmented = new double[m.getRowCount()];
        Map<Nutrients, Ratio> nf = facts.getFacts();
        List<Ratio> botRow = new ArrayList<>();

        int count = 0;

        for (Nutrients n : nf.keySet()) {
            List<Ratio> row = new ArrayList<>();
            for (int i = 0; i < m.getColumnCount(); i++) {
                row.add(ingredients.get(i).getNutrients().get(n));
            }
            m.setRow(row, count);
            augmented[count] = nf.get(n).getValue();
            count++;
        }
        for (int c = 0; c < m.getColumnCount(); c++) {
            botRow.add(new Ratio(1,1));
        }
        m.setRow(botRow, count);
        augmented[count] = 1.0;

        solveMatrix(m.getMatrix(), augmented);
    }

    // MODIFIES: this
    // EFFECTS: finds the unique solution (proportions) to a matrix containing ingredient nutrient / mass ratios
    // augmented with the recipes' nutrition facts. sets proportions to empty list if no solution is found
    public void solveMatrix(double[][] matrix, double[]augment) {
        RealMatrix coefficients = new Array2DRowRealMatrix(matrix);

        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();

        RealVector constants = new ArrayRealVector(augment);

        if (!solver.isNonSingular()) {
            proportions = new ArrayList<>();
            return;
        }
        RealVector solution = solver.solve(constants);


        for (int i = 0; i < matrix.length; i++) {
            proportions.add(i, solution.getEntry(i));
        }


    }

    // EFFECTS: returns a String for the recipe with the given format:
    // * recipeName *
    // ingredientName, Proportion: proportion to two decimal places
    // ...
    @Override
    public String toString() {
        String result = "* " + name + " *";
        DecimalFormat twoDecimals = new DecimalFormat("#.00");
        int count = 0;

        for (Ingredient i : ingredients) {
            result = result.concat(System.lineSeparator() + i.getName() + ", Proportion: "
                    + twoDecimals.format(proportions.get(count)));
            count++;
        }
        return result;
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

    // EFFECTS: returns recipe as a JSON object
    @Override
    public JSONObject toJson() {

        JSONObject result = new JSONObject();
        JSONArray ingList = new JSONArray();

        result.put("name", name);

        result.put("nutrition", facts.toJson());

        for (Ingredient i : ingredients) {
            ingList.put(i.toJson());
        }

        result.put("ingredients", ingList);

        return result;
    }
}
