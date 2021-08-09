package ui;

// Represents an observer of changes in a recipe list.
public interface RecipesObserver {
    // MODIFIES: this
    // EFFECTS: updates the observer status of this
    void update();

    // EFFECTS: returns true if this should still be accessible
    boolean visible();
}
