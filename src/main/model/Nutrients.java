package model;

// Valid core nutrients
public enum Nutrients {
    TOTAL_FAT("Total Fat"),
    SATURATED_FAT("Saturated Fat"),
    CARBOHYDRATE("Carbohydrate"),
    POTASSIUM("Potassium"),
    FIBRE("Fibre"),
    SUGARS("Sugars"),
    PROTEIN("Protein");

    private final String name;

    Nutrients(String name) {
        this.name = name;
    }

    //EFFECTS: returns type of nutrient
    public String getValue(String name) {
        return this.name;
    }
}
