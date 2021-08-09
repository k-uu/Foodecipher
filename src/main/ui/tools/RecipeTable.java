package ui.tools;

import model.*;
import ui.FoodecipherGUI;
import ui.RatioDialog;
import ui.RecipeDialog;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Represents a table to input values required to create a new recipe
public class RecipeTable extends JPanel {

    private JButton button;
    private JTable table;
    private List<Nutrients> nutrients;
    private FoodecipherGUI frame;

    private int servingSize;
    private String recipeName;

    // EFFECTS: creates a table with n rows and n+1 columns that accepts integer values. The first column contains
    // recipe names, the last row contains nutrition fact ratios and the remaining cells contain ingredient nutrient
    // ratios
    public RecipeTable(FoodecipherGUI frame) {

        super(new GridLayout(2,0));

        setName("RecipeTable");
        this.frame = frame;

        RecipeDialog d = new RecipeDialog(frame);
        d.setLocationRelativeTo(frame);
        d.setVisible(true);
        nutrients = d.getNutrients();
        recipeName = d.getChosenName();
        servingSize = d.getServingSize();

        RatioDialog ratioDialog = new RatioDialog(frame);
        ratioDialog.setLocationRelativeTo(frame);

        table = new JTable(new RecipeTableModel(nutrients));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        table.setDefaultEditor(Ratio.class, new RatioEditor(ratioDialog));

        button = new JButton("Make recipe!");

        setButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertToRecipe();
            }
        });

        add(button);
    }

    // REQUIRES: cells with column index > 0 are type Ratio and index 0 are type String
    // MODIFIES: this
    // EFFECTS: takes data from JTable and makes it into a Recipe. Displays message if unsuccessful
    // otherwise plays sound
    private void convertToRecipe() {
        TableModel data = table.getModel();

        List<Ingredient> ingredients = new ArrayList<>();

        for (int r = 0; r < data.getRowCount() - 1; r++) {
            Ingredient ing = new Ingredient((String)data.getValueAt(r, 0));
            for (int c = 1; c < data.getColumnCount(); c++) {
                ing.addNutrientRatio(nutrients.get(c - 1), (Ratio)data.getValueAt(r, c));
            }
            ingredients.add(ing);
        }

        checkName();

        Recipe r = new Recipe(recipeName, convertToNutritionFacts(data), ingredients);
        if (r.getProportions().size() != 0) {
            frame.addRecipe(r); // add successful recipe to recipe list
            playSound();
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(
                    RecipeTable.this,
                    "Unable to find proportions, please try a different combination of ingredients and/or"
                            + " nutrients",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    // EFFECTS: extracts NutritionFacts from data
    private NutritionFacts convertToNutritionFacts(TableModel data) {

        Map<Nutrients, Integer> facts = new HashMap<>();
        for (int i = 1; i < table.getColumnCount(); i++) {
            Ratio ratio = (Ratio)data.getValueAt(table.getRowCount() - 1, i);
            facts.put(nutrients.get(i - 1), ratio.getNumerator());
        }
        return new NutritionFacts(servingSize, facts);
    }

    // MODIFIES: this
    // EFFECTS: ensures that recipe names are unique
    private void checkName() {
        for (Recipe r :frame.getRecipes()) {
            if (r.getName().equals(recipeName)) {
                recipeName = r.getName() + "~";
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an ActionListener to make recipe button
    public void setButtonActionListener(ActionListener listener) {
        button.addActionListener(listener);
    }

    // EFFECTS: plays a wav sound file.
    public void playSound() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("./data/bonappetit.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
