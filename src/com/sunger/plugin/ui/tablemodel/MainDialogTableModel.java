package com.sunger.plugin.ui.tablemodel;

import com.sunger.plugin.entity.RowEntity;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by sunger on 16/7/24.
 */
public class MainDialogTableModel extends AbstractTableModel {

    private String[] header = {"Order", "Name", "Rate", ""};
    private List<RowEntity> itemData;

    public MainDialogTableModel(List<RowEntity> list) {
        this.itemData = list;
    }

    @Override
    public int getRowCount() {
        return this.itemData.size();
    }


    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (rowIndex == itemData.size())
            return "";
        switch (columnIndex) {
            case 0:
                return rowIndex + 1 + "";
            case 1:
                RowEntity entity = itemData.get(rowIndex);
                return entity.getFolderName();
            case 2:
                return this.itemData.get(rowIndex).getRate();
            case 3:
                return "Delete";
            default:
                return "-";
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    public void removeRow(int row) {
        itemData.remove(row);
        fireTableRowsDeleted(row, row);
    }


    public void addRow(RowEntity entity) {
        itemData.add(entity);
        fireTableDataChanged();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        RowEntity entity = itemData.get(rowIndex);
        String newValue = aValue + "";
        switch (columnIndex) {
            case 1:
                entity.setFolderName((String) aValue);
                break;
            case 2:
                entity.setRate(Double.parseDouble(newValue));
                break;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column != 0;
    }

    public List<RowEntity> getAllRows() {
        return itemData;
    }


}
