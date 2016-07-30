package com.sunger.plugin.ui.control;

import com.sunger.plugin.ui.tablemodel.MainDialogTableModel;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by sunger on 16/7/24.
 */

public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private JXTable jxTable;
    private DefaultTableModel model;

    public ButtonEditor(JTextField textField, JXTable jxTable) {
        super(textField);
        this.setClickCountToStart(1);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> {
            MainDialogTableModel model = (MainDialogTableModel) jxTable.getModel();
            model.removeRow(jxTable.getSelectedRow());
        });
    }

    @Override
    public Component getTableCellEditorComponent(final JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }
}
