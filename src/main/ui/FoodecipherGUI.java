package ui;

import model.Recipe;
import model.RecipeList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.RecipeTable;
import ui.tools.RecipesEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// A graphical user interface for Foodecipher. Multiple elements of this GUI were adapted from Oracle Java Tutorials:
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/
public class FoodecipherGUI extends JFrame {

    private final JTabbedPane panes = new JTabbedPane();
    private JMenuItem makeRecipe;
    private JMenuItem loadRecipes;
    private JMenuItem saveRecipes;
    private JMenuItem help;
    private JLabel saveStatus;
    private RecipeTable table;
    private RecipesEditor editor;
    private RecipeList recipes;

    private int tabCount;

    private static final JPanel HOME_PANEL = initHome();

    // MODIFIES: this
    // EFFECTS: creates home panel
    private static JPanel initHome() {
        ImageIcon icon = new ImageIcon("./data/home.png");
        JPanel p = new JPanel();
        p.add(new JLabel(icon));
        p.setName("Home");
        return p;
    }


    private static final JPanel HELP_PANEL = initHelp();

    // MODIFIES: this
    // EFFECTS: creates help panel
    private static JPanel initHelp() {
        ImageIcon icon = new ImageIcon("./data/help.png");
        JPanel p = new JPanel();
        p.add(new JLabel(icon));
        p.setName("Help");
        return p;
    }

    // EFFECTS: initializes window, menu and home panel in tab panels.
    public FoodecipherGUI() {

        super("Foodecipher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initMenu();
        add(panes);
        tabCount = 0;
        recipes = new RecipeList();

        initTab(HOME_PANEL);

        setSize(900, 520);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: add a new Panel to panes and set focus on it
    private void initTab(JPanel panel) {
        panes.add(panel);
        tabCount = panes.getTabCount() - 1;
        initTabComponent(tabCount, panel.getName());
        panes.setSelectedIndex(tabCount);
    }

    // MODIFIES: panes
    // EFFECTS: adds a ClosableTab to the associated panel
    private void initTabComponent(int i, String name) {
        panes.setTabComponentAt(i, new ClosableTab(panes, name));
    }

    // MODIFIES: this
    // EFFECTS: constructs the menu bar and available menu options
    private void initMenu() {

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        saveStatus = new JLabel("");

        addMakeOption(menu);
        addViewOption(menu);
        menu.addSeparator();
        addSaveOption(menu);
        addLoadOption(menu);
        addHelpOption(menu);

        menuBar.add(menu);
        menuBar.add(new JSeparator());
        menuBar.add(saveStatus);

        setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: adds a save option to menu
    private void addSaveOption(JMenu menu) {
        saveRecipes = new JMenuItem("Save recipes");
        saveRecipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonWriter writer = new JsonWriter("./data/recipes.json");
                try {
                    writer.open();
                    setSaveStatus(" Saved recipes! ");
                } catch (FileNotFoundException exception) {
                    setSaveStatus(" The storage file was not found ");
                }
                writer.write(recipes);
                writer.close();
            }
        });
        menu.add(saveRecipes);
    }

    // MODIFIES: this
    // sets the save status in the menu bar
    private void setSaveStatus(String status) {
        saveStatus.setText(status);
    }

    // MODIFIES: this
    // EFFECTS: adds a load option to menu
    private void addLoadOption(JMenu menu) {
        loadRecipes = new JMenuItem("Load recipes");
        loadRecipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonReader reader = new JsonReader("./data/recipes.json");
                try {
                    recipes = reader.read();
                    setSaveStatus(" Loaded recipes! ");
                } catch (IOException exception) {
                    setSaveStatus(" Unable to load recipes ");
                }
            }
        });
        menu.add(loadRecipes);
    }

    // MODIFIES: this
    // EFFECTS: adds a make recipe option to menu
    private void addMakeOption(JMenu menu) {
        makeRecipe = new JMenuItem("Make a new recipe");
        makeRecipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table = new RecipeTable(FoodecipherGUI.this);
                initTab(table);
                table.requestFocusInWindow();
            }
        });
        menu.add(makeRecipe);
    }

    // MODIFIES: this
    // EFFECTS: adds a help option to menu
    private void addHelpOption(JMenu menu) {
        help = new JMenuItem("Help");
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initTab(HELP_PANEL);
            }
        });
        menu.add(help);
    }

    // MODIFIES: this
    // EFFECTS: adds a view option to menu. If there are no recipes to view, display an error message instead
    private void addViewOption(JMenu menu) {
        JMenuItem view = new JMenuItem("View recipes");
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (recipes.getRecipes().size() != 0) {
                    editor = new RecipesEditor(FoodecipherGUI.this);
                    initTab(editor);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(
                            FoodecipherGUI.this,
                            "There are no recipes to view", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        menu.add(view);
    }



    // REQUIRES: the new recipe name doesn't already exists in recipes
    // MODIFIES: this
    // EFFECTS: adds a recipe to the recipe list
    public void addRecipe(Recipe r) {
        recipes.addRecipe(r);
    }

    // MODIFIES: this
    // EFFECTS: removes recipe from recipe list
    public boolean removeRecipe(Recipe r) {
        return recipes.removeRecipe(r);
    }

    // EFFECTS: returns the current list of recipes
    public List<Recipe> getRecipes() {
        return recipes.getRecipes();
    }

}
