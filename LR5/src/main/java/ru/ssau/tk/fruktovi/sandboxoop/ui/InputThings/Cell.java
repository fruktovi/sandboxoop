package ru.ssau.tk.fruktovi.sandboxoop.ui.InputThings;



import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class Cell extends AbstractCellEditor implements TableCellEditor {
    private final JTextField textField;

    public Cell() {
        textField = new JTextField();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DoubleNum());
    }

    @Override
    public Object getCellEditorValue() {
        try {
            return Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        textField.setText(value != null ? value.toString() : "");
        return textField;
    }
}
