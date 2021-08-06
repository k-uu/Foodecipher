package ui.tools;

import model.Nutrients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class RecipeDialog extends JDialog implements ActionListener, PropertyChangeListener {

    private String chosenName;
    private int servingSize;
    private List<Nutrients> nutrients;
    private JOptionPane optionPane;
    private JTextField nameField;
    private JTextField servingSizeField;

    private String enter = "Enter";

    public RecipeDialog(JFrame frame) {
        super(frame, true);
        setTitle("New Recipe");

        nutrients = new ArrayList<>();

        nameField = new JTextField(10);
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        servingSizeField = new JFormattedTextField(format);

        String[] msgs = {"Recipe name:", "Select Core Nutrients:", "What's the serving size on the Nutrition Facts?"};

        Object[] array = {msgs[0], nameField, msgs[1], initCheckboxes(), msgs[2], servingSizeField};
        Object[] option = {enter};

        optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,
                null, option, option[0]);

        setContentPane(optionPane);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // MODIFIES: this
        // EFFECTS: hides dialog instead of closing it when user attempts to close it
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                optionPane.setValue(JOptionPane.CLOSED_OPTION);
            }
        });

        nameField.addActionListener(this);
        servingSizeField.addActionListener(this);
        optionPane.addPropertyChangeListener(this);

        pack();
    }

    private Object[] initCheckboxes() {
        List<Object> result = new ArrayList<>();
        for (Nutrients n : Nutrients.values()) {
            JCheckBox b = new JCheckBox(n.getValue());
            b.addActionListener(this);
            b.setActionCommand(n.getValue());
            result.add(b);
        }

        return result.toArray();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Total Fat": nutrients.add(Nutrients.TOTAL_FAT);
                break;
            case "Saturated Fat": nutrients.add(Nutrients.SATURATED_FAT);
                break;
            case "Potassium": nutrients.add(Nutrients.POTASSIUM);
                break;
            case "Carbohydrate": nutrients.add(Nutrients.CARBOHYDRATE);
                break;
            case "Sugars": nutrients.add(Nutrients.SUGARS);
                break;
            case "Fibre": nutrients.add(Nutrients.FIBRE);
                break;
            case "Protein": nutrients.add(Nutrients.PROTEIN);
        }



    }

    private void validateInput(Object value) {

        String size = servingSizeField.getText();

        if (enter.equals(value)) {
            if (nutrients.size() == 0) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(
                        RecipeDialog.this,
                        "Select at Least 1 Core Nutrient", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            } else if (size.equals("") || Integer.parseInt(size) <= 0) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(
                        RecipeDialog.this,
                        "Provide a valid serving size", "Try again",
                        JOptionPane.ERROR_MESSAGE);
                servingSizeField.setText(null);
            } else {
                servingSize = Integer.parseInt(size);
                clearAndHide();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String prop = evt.getPropertyName();

        if (isVisible()
                && (evt.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop)
                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }

            chosenName = nameField.getText();

            validateInput(value);

            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
        }
    }

    private void clearAndHide() {
        setVisible(false);
        nameField.setText(null);
    }

    public List<Nutrients> getNutrients() {
        return nutrients;
    }

    public String getChosenName() {
        return chosenName;
    }

    public int getServingSize() {
        return servingSize;
    }
}
