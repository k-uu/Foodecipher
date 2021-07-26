package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// User interface for Foodecipher. Inspired by TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class FoodecipherApp {

    private RecipeList recipes;
    private Scanner sc;

    // EFFECTS: intializes and runs FoodecipherApp
    public FoodecipherApp() {
        recipes = new RecipeList();
        sc = new Scanner(System.in);
        run();
    }

    // MODIFIES: this
    // EFFECTS: begins runtime environment in console
    private void run() {

        boolean isRunning = true;
        String input = null;

        while (isRunning) {

            System.out.println();
            displayMenu();
            input = sc.nextLine();
            input = input.toLowerCase();

            if (input.equals("q")) {
                isRunning = false;
            } else {
                processInput(input);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: process valid user input
    private void processInput(String input) {

        if (input.equals("m")) {
            makeRecipe();
            return;
        }

        viewRecipes();

        switch (input) {
            case "v":
                System.out.print("Choose a recipe name to view:");
                input = sc.nextLine();
                viewRecipe(input);
                break;
            case "e":
                System.out.print("Choose a recipe name to edit:");
                input = sc.nextLine();
                editRecipe(input);
                break;
            case "r":
                System.out.print("Choose a recipe name to remove:");
                input = sc.nextLine();
                removeRecipe(input);
                break;
            default: System.out.println("Unknown command :(");
        }
    }


    // MODIFIES: this
    // EFFECTS: make a new recipe
    private void makeRecipe() {

        System.out.print("New recipe name: ");
        String name = sc.nextLine();

        for (Recipe r : recipes.getRecipes()) {
            if (r.getName().equals(name)) {
                System.out.println("This name is already taken");
                return;
            }
        }
        displayNutrients();
        System.out.println("Number of Core Nutrients: ");
        int num = sc.nextInt();
        sc.nextLine();
        List<Nutrients> nutrients = getNutrients(num);

        Recipe recipe = new Recipe(name, getNutritionFacts(nutrients), getIngredients(nutrients));

        recipes.addRecipe(recipe);

    }



    // EFFECTS: returns a list of nutrients given by user:
    private List<Nutrients> getNutrients(int nutrients) {

    }

    // EFFECTS: returns NutritionFacts given by user
    private NutritionFacts getNutritionFacts(List<Nutrients> nutrients) {
    }

    // EFFECTS: returns a list of ingredients given by user
    private List<Ingredient> getIngredients(List<Nutrients> nutrients) {

        List<Ingredient> ingredients = new ArrayList<>();
        boolean isRunning = true;

        while (isRunning) {

            System.out.println("(a) - add another ingredient");
            String input = sc.nextLine();
            if (input.equals("a")) {
                System.out.print("Ingredient name: ");
                input = sc.nextLine();
                ingredients.add(getNutrientRatios(new Ingredient(input), nutrients));

            } else {
                isRunning = false;
            }
        }
        return ingredients;
    }

    // MODIFIES: i
    // EFFECTS: adds user input nutrient ratios to Ingredient
    private Ingredient getNutrientRatios(Ingredient i, List<Nutrients> nutrients) {
    }


    // gets a valid ratio from the user;
    private Ratio getRatio() {
    }

    // gets a valid nutrient from the user;
    private Nutrients getNutrient() {
    }

    // displays list of valid nutrients
    private void displayNutrients() {

    }

    // MODIFIES: this
    // EFFECTS: edit aspects of an existing recipe
    private void editRecipe(String name) { }


    // EFFECTS: view a list of recipe names
    private void viewRecipes() {

        System.out.println(recipes);
    }

    // EFFECTS: view an existing recipes
    private void viewRecipe(String name) {

        for (Recipe r : recipes.getRecipes()) {
            if (r.getName().equals(name)) {
                System.out.println(r);
                return;
            }
        }
        System.out.println("No recipe to be found...");
    }

    // MODIFIES: this
    // EFFECTS: remove an existing recipe
    private void removeRecipe(String name) {

        for (Recipe r : recipes.getRecipes()) {
            if (r.getName().equals(name)) {
                recipes.removeRecipe(r);
                return;
            }
        }
        System.out.println("No recipe to be found...");
    }

    // EFFECTS: show functions menu
    private void displayMenu() { }

}
