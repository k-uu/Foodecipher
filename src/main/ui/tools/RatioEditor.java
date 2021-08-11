package ui.tools;

import model.Ratio;
import ui.RatioDialog;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;

// Represents a table cell editor for Ratios
public class RatioEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private Ratio currentRatio;
    private final JButton button;

    private final RatioDialog dialog;


    protected static final String EDIT = "edit";

    // EFFECTS: instantiates the button and dialog associated with editing a Ratio cell
    public RatioEditor(RatioDialog dialog) {

        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);

        this.dialog = dialog;

    }

    // MODIFIES: this
    // EFFECTS: checks if the user clicked a cell. If so, open the RatioDialog
    @Override
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {

            button.setText(currentRatio.toString());
            dialog.setVisible(true);

            fireEditingStopped();
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentRatio = (Ratio)value;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (dialog.getRatio() == null) {
            return currentRatio;
        } else {
            return dialog.getRatio();
        }
    }
}
