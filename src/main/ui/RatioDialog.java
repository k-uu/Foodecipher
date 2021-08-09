package ui;

import model.Ratio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// Represents a reusable ratio input dialog
public class RatioDialog extends JDialog implements ActionListener, PropertyChangeListener {

    private Ratio chosenRatio;
    private JTextField numeratorField;
    private JTextField denominatorField;
    private JOptionPane optionPane;

    private String enter = "Enter";
    private String cancel = "Cancel";

    // EFFECTS: create option pane
    public RatioDialog(JFrame frame) {
        super(frame, true);

        setTitle("Ratio Selection");
        numeratorField = new JTextField(10);
        denominatorField = new JTextField(10);
        String msg1 = "Provide the amount of the nutrient:";
        String msg2 = "In this serving size:";

        Object[] array = {msg1, numeratorField, msg2, denominatorField};
        Object[] options = {enter, cancel};

        optionPane = new JOptionPane(array,
                JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,
                null,
                options, options[0]);

        setContentPane(optionPane);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // MODIFIES: this
        // EFFECTS: hides dialog instead of closing it when user attempts to close it
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                optionPane.setValue(JOptionPane.CLOSED_OPTION);
            }
        });

        numeratorField.addActionListener(this);
        denominatorField.addActionListener(this);
        optionPane.addPropertyChangeListener(this);

        pack();
    }

    // MODIFIES: this
    // EFFECTS: sets optionPane value to "enter" when the text fields are edited
    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(enter);
    }

    // MODIFIES: this
    // EFFECTS: checks if ENTER was pressed and the inputs result in a valid ratio.
    private void validateText(Object value) {

        if (enter.equals(value)) {
            try {
                chosenRatio = new Ratio(Integer.parseInt(numeratorField.getText()),
                        Integer.parseInt(denominatorField.getText()));
                System.out.println(chosenRatio); // TEST
                clearAndHide();
            } catch (IllegalArgumentException e) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(
                        RatioDialog.this,
                        "Sorry, you gave an invalid ratio",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
                numeratorField.setText(null);
                denominatorField.setText(null);
                numeratorField.requestFocusInWindow();
            }
        } else {
            chosenRatio = null;
            clearAndHide();
        }
    }

    // MODIFIES: this
    // EFFECTS: determines the result of a property change in the JOptionPane
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

            validateText(value);

            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
        }
    }

    // MODIFIES: this
    // EFFECTS: clears text fields and sets dialog visibility to false
    public void clearAndHide() {
        numeratorField.setText(null);
        denominatorField.setText(null);
        setVisible(false);
    }

    public Ratio getRatio() {
        return chosenRatio;
    }
}