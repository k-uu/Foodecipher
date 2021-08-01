package ui;

import model.*;

import java.util.*;

// Foodecipher by: Oskar Blyt
// User interface for Foodecipher. Inspired by TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class FoodecipherApp {

    private RecipeList recipes;
    private Scanner sc;

    // EFFECTS: initializes and runs FoodecipherApp
    public FoodecipherApp() {
        recipes = new RecipeList();
        sc = new Scanner(System.in);
        run();
    }

    // MODIFIES: this
    // EFFECTS: begins runtime environment in console
    private void run() {

        System.out.println("Welcome to Foodecipher!");
        System.out.println();
        boolean isRunning = true;
        String input = null;

        while (isRunning) {

            displayMenu();
            input = sc.nextLine();
            input = input.toLowerCase();

            if (input.equals("q")) {
                isRunning = false;
            } else if (input.equals("h")) {
                displayHelp();
            } else {
                processInput(input);
                System.out.println();
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
        switch (input) {
            case "v":
                viewRecipes();
                System.out.print("Choose a recipe name to view:");
                input = sc.nextLine();
                viewRecipe(input);
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
        List<Nutrients> nutrients = getNutrients();

        recipes.addRecipe(new Recipe(name, getNutritionFacts(nutrients), getIngredients(nutrients)));

        List<Recipe> r = recipes.getRecipes();

        int last = r.size() - 1;

        if (r.get(last).getProportions().size() == 0) {
            System.out.println("WARNING: Could not find proportions for" + r.get(last).getName());
            recipes.removeRecipe(r.get(last));
        }
    }



    // EFFECTS: returns a list of nutrients given by user:
    private List<Nutrients> getNutrients() {

        List<Nutrients> nutrients = new ArrayList<>();
        System.out.println("Select your Core Nutrients for this recipe. Enter 'y' to select or any other character"
                + " to continue");

        String input;
        for (Nutrients n : Nutrients.values()) {
            System.out.print("Add " + n.getValue() + " ? ");
            input = sc.nextLine();
            if (input.equals("y")) {
                nutrients.add(n);
            }
        }
        if (nutrients.size() == 0) {
            System.out.println("Please select at least one Core Nutrient");
            return getNutrients();
        }
        return nutrients;
    }

    // EFFECTS: returns NutritionFacts given by user
    private NutritionFacts getNutritionFacts(List<Nutrients> nutrients) {

        Map<Nutrients, Integer> map = new HashMap<>();
        System.out.print("What is the serving size on the Nutrition Facts label? : ");
        int servingSize = sc.nextInt();
        sc.nextLine();
        while (servingSize <= 0) {
            System.out.println("Invalid serving size. Try again");
            servingSize = sc.nextInt();
            sc.nextLine();
        }

        for (Nutrients n : nutrients) {
            System.out.print("How much " + n.getValue() + " is in the serving size? : ");
            Integer amount = sc.nextInt();
            sc.nextLine();
            map.put(n, amount);
        }

        return new NutritionFacts(servingSize, map);
    }

    // EFFECTS: returns a list of ingredients given by user
    private List<Ingredient> getIngredients(List<Nutrients> nutrients) {

        List<Ingredient> ingredients = new ArrayList<>();
        System.out.println("Please provide " + (nutrients.size() + 1) + " ingredients");

        for (int i = 0; i <= nutrients.size(); i++) {

            System.out.print("Ingredient name: ");
            String input = sc.nextLine();
            ingredients.add(getNutrientRatios(new Ingredient(input), nutrients));

        }
        return ingredients;
    }

    // MODIFIES: i
    // EFFECTS: adds user input nutrient ratios to Ingredient
    private Ingredient getNutrientRatios(Ingredient i, List<Nutrients> nutrients) {

        System.out.println("Please provide " + nutrients.size() + " Nutrient / mass ratio(s)");
        for (int nut = 0; nut < nutrients.size(); nut++) {

            Nutrients n = getNutrient(nutrients);
            Ratio r = getRatio(n);

            while (i.getNutrients().containsKey(n)) {
                System.out.println("WARNING: Please provide only one Ratio per Nutrient");
                n = getNutrient(nutrients);
                r = getRatio(n);
            }

            i.addNutrientRatio(n, r);

        }
        return i;
    }


    // gets a valid ratio from the user;
    private Ratio getRatio(Nutrients nutrient) {

        Ratio r;

        System.out.print("Amount of " + nutrient.getValue() + " in ingredient: ");
        int numerator = sc.nextInt();
        sc.nextLine();
        System.out.print("in this serving size: ");
        int denominator = sc.nextInt();
        sc.nextLine();

        try {
            r = new Ratio(numerator, denominator);
        } catch (IllegalArgumentException e) {
            System.out.println("WARNING: Not a valid Ratio. Please try again");
            return getRatio(nutrient);
        }
        return r;
    }

    // gets a valid nutrient from the user;
    private Nutrients getNutrient(List<Nutrients> nutrients) {

        System.out.println("Selected Core Nutrients: ");
        for (Nutrients n: nutrients) {
            System.out.println("- " + n.getValue());
        }
        System.out.println("Select a Nutrient type: ");
        String input = sc.nextLine();
        for (Nutrients n: nutrients) {
            if (n.getValue().equals(input)) {
                return n;
            }
        }
        System.out.println("WARNING: Please provide one of the selected Nutrients");
        return getNutrient(nutrients);
    }

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
        System.out.println("That recipe name was not found...");
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
        System.out.println("That recipe name was not found...");
    }

    // EFFECTS: show help menu
    private void displayHelp() {
        String help = "Foodecipher will ask you for Core Nutrients. Pick Nutrients that you "
                + "think are contained in most of the ingredients."
                + System.lineSeparator() + "When Foodecipher asks for the Nutrient / mass ratios, search the internet"
                + " for the Nutrition Facts of each ingredient you chose." + System.lineSeparator()
                + "When Foodecipher asks for Nutrition Facts, provide the serving size and Nutrient amounts written on"
                + " the label of your food item." + System.lineSeparator()
                + " * Ensure that your units for all inputs are consistent! * " + System.lineSeparator()
                + "If a recipe contains a negative proportion, try again with a different combination of Nutrients"
                + " / ingredients";
        System.out.println(help);
        System.out.println();
    }

    // EFFECTS: show functions menu
    private void displayMenu() {

        String menu = "List of possible commands:" + System.lineSeparator()
                + "(m) - Make a new recipe" + System.lineSeparator()
                + "(v) - View an existing recipe" + System.lineSeparator()
                + "(r) - remove an existing recipe" + System.lineSeparator()
                + "(h) - help" + System.lineSeparator()
                + "(q) - quit";
        System.out.println(menu);
    }

}
