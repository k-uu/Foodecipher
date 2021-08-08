package ui.tools;

import model.Recipe;
import ui.FoodecipherGUI;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a mutable list of recipes created so far
public class RecipesEditor extends JPanel implements ListSelectionListener {

    private DefaultListModel<LabeledRecipe> listModel;
    private FoodecipherGUI frame;
    private JList<LabeledRecipe> list;
    private JButton removeButton;
    private JButton viewButton;


    private static final String removeString = "Remove";
    private static final String viewString = "View";

    // EFFECTS: instantiates list and adds recipes created so far to it
    public RecipesEditor(FoodecipherGUI frame) {
        super(new BorderLayout());
        setName("Recipe Editor");
        listModel = new DefaultListModel();
        this.frame = frame;

        for (Recipe r : frame.getRecipes()) {
            listModel.addElement(new LabeledRecipe(r));
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        removeButton = new JButton(removeString);
        viewButton = new JButton(viewString);

        removeButton.addActionListener(new RemoveListener());
        viewButton.addActionListener(new ViewListener());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

        buttonPane.add(removeButton);
        buttonPane.add(viewButton);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeButton.setEnabled(false);
                viewButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
                viewButton.setEnabled(true);
            }
        }
    }

    // Represents a listener for removing a recipe from the list
    private class RemoveListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: removes the selected recipe from the JList and RecipeList
        @Override
        public void actionPerformed(ActionEvent e) {

            int index = list.getSelectedIndex();

            frame.removeRecipe(list.getSelectedValue().getRecipe());
            listModel.remove(index);
            System.out.println(frame.getRecipes());

            int size = listModel.getSize();

            if (size == 0) {
                removeButton.setEnabled(false);
            } else {
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }
            }

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

    // Represents a listener for viewing an existing recipe
    private class ViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Recipe r = list.getSelectedValue().getRecipe();
            if (r != null) {
                JOptionPane.showMessageDialog(frame,
                        r.toString(),
                        "Viewed Recipe",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    // Represents a wrapper for a Recipe that has a recipe name field.
    private class LabeledRecipe {

        private final String label;
        private final Recipe recipe;

        LabeledRecipe(Recipe r) {
            this.label = r.getName();
            this.recipe = r;
        }

        public Recipe getRecipe() {
            return recipe;
        }

        @Override
        public String toString() {
            return label;
        }
    }
}
