package ui.tools;

import model.Nutrients;
import model.Ratio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeTable extends JPanel {

    // EFFECTS: creates an empty table with n rows and n+1 columns that accepts integer values.
    // Each column has label with an un-editable combo box.
    public RecipeTable(JFrame frame) {

        super(new GridLayout(1,0));

        setName("RecipeTable");

        List<Nutrients> n = new ArrayList<>();
        n.add(Nutrients.SATURATED_FAT);
        n.add(Nutrients.SUGARS);
        n.add(Nutrients.PROTEIN);

        RatioDialog dialog = new RatioDialog(frame);
        dialog.setLocationRelativeTo(frame);

        JTable table = new JTable(new RecipeTableModel(n));

        JScrollPane scrollPane = new JScrollPane(table);

        table.setDefaultEditor(Ratio.class,
                new RatioEditor(dialog));

        add(scrollPane);
    }

}
