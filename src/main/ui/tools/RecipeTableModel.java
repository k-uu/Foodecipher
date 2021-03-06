package ui.tools;

import model.Nutrients;
import model.Ratio;

import javax.swing.table.AbstractTableModel;
import java.util.List;

// Represents a table model for a creating a Recipe
public class RecipeTableModel extends AbstractTableModel {

    private String[] columnNames;
    private Object[][] data;


    // EFFECTS: constructs a table model with dimensions based on the size of nutrients.
    public RecipeTableModel(List<Nutrients> nutrients) {

        columnNames = new String[nutrients.size() + 1];

        columnNames[0] = "Ingredients";
        for (int i = 0; i < nutrients.size(); i++) {
            columnNames[i + 1] = nutrients.get(i).getValue();
        }

        data = new Object[columnNames.length + 1][columnNames.length];
        data[columnNames.length][0] = "Nutrition Facts";

        for (int i = 1; i < columnNames.length; i++) {
            for (int j = 0; j <= columnNames.length; j++) {
                data[j][i] = new Ratio(0, 1);
            }
        }
    }

    // EFFECTS: returns the column name
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return columnNames.length + 1;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (data[rowIndex][columnIndex] == null) {
            return "ing " + rowIndex;
        } else {
            return data[rowIndex][columnIndex];
        }
    }

    // EFFECTS: gets the type contained in column c.
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


    // EFFECTS: sets value of cell
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = value;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    // EFFECTS: returns true if the cell is editable
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        if (columnIndex == 0 && rowIndex == columnNames.length) {
            return false;
        } else {
            return true;
        }
    }
}
