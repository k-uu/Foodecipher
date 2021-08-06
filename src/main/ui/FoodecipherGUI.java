package ui;

import model.Recipe;
import model.RecipeList;
import ui.tools.RecipeTable;
import ui.tools.RecipesEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// A graphical user interface for Foodecipher. Inspired by
public class FoodecipherGUI extends JFrame {

    private final JTabbedPane panes = new JTabbedPane();
    private JMenuItem makeRecipe;
    private JMenuItem loadRecipes;
    private JMenuItem saveRecipes;
    private JMenuItem help;
    private RecipeTable table;
    private RecipesEditor editor;
    private RecipeList recipes;

    private int tabCount;

    private static final JPanel HOME_PANEL = initHome();

    private static JPanel initHome() {
        JPanel p = new JPanel();
        p.add(new JLabel("sas"));
        p.setName("Home");
        return p;
    }


    private static final JPanel HELP_PANEL = initHelp();

    private static JPanel initHelp() {
        JPanel p = new JPanel();
        p.add(new JLabel("succ"));
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
    // EFFECTS: add a new Tab and Panel to panes.
    private void initTab(JPanel panel) {
        panes.add(panel);
        tabCount = panes.getTabCount() - 1;
        initTabComponent(tabCount, panel.getName());
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
        menuBar.add(menu);

        addMakeOption(menu);
        addLoadOption(menu);
        addSaveOption(menu);
        addHelpOption(menu);

        setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: adds a save option to menu
    private void addSaveOption(JMenu menu) {
        saveRecipes = new JMenuItem("Save recipes");
        saveRecipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(saveRecipes);
    }

    // MODIFIES: this
    // EFFECTS: adds a load option to menu
    private void addLoadOption(JMenu menu) {
        loadRecipes = new JMenuItem("Load recipes");
        loadRecipes.getAccessibleContext().setAccessibleDescription(
                "Load the recipes that were last saved");
        loadRecipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

            }
        });
        menu.add(help);
    }

    // REQUIRES: the new recipe name doesn't already exists in recipes
    // MODIFIES: this
    // EFFECTS: adds a recipe to the recipe list
    public void addRecipe(Recipe r) {
        recipes.addRecipe(r);
    }

    public List<Recipe> getRecipes() {
        return recipes.getRecipes();
    }

}
