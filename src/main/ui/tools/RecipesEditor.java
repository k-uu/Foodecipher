package ui.tools;

import model.RecipeList;
import ui.RecipeCell;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RecipesEditor extends JPanel implements ListSelectionListener {

    private DefaultListModel<RecipeCell> listModel;
    private RecipeList recipes;

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
