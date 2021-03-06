package model;

// Valid core nutrients
public enum Nutrients {
    TOTAL_FAT("Total Fat"),
    SATURATED_FAT("Saturated Fat"),
    POTASSIUM("Potassium"),
    CARBOHYDRATE("Carbohydrate"),
    SUGARS("Sugars"),
    FIBRE("Fibre"),
    PROTEIN("Protein");

    private final String name;

    Nutrients(String name) {
        this.name = name;
    }

    //EFFECTS: returns type of nutrient
    public String getValue() {
        return this.name;
    }
}
