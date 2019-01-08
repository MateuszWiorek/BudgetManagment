package com.mateuszwiorek.budget;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Vector;


public class CenterModel extends AbstractTableModel {
    protected ArrayList<Money> incomesData;
    protected Vector columnNames;


    @Override
    public int getRowCount() {
        return this.incomesData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Money income = (Money) incomesData.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return income.getName();
            case 1:
                return income.getValue();
            case 2:
                return income.getDate();
            case 3:
                return income.getMoneyType();
            case 4:
                return income.getId();
            default:
                return new Object();
        }
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }


    public String getColumnName(int columnIndex) {
        return (String) columnNames.elementAt(columnIndex);
    }

    public boolean isCellEditable(int row, int column) {
        return true;
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex){
        Money income = (Money) incomesData.get(rowIndex);
        switch (columnIndex){
            case 0: {
                income.setName(value.toString());
                break;
            }
            case 1: {
                income.setValue(Integer.parseInt((String) value.toString()));
                break;
            }
            default: {
                break;
            }
        }
        new BudgetDAO().editRow(income);
    }
    public void remove (int rowIndex){

                incomesData.remove(rowIndex);
                fireTableDataChanged();


    }

    private void initColumnNames() {
        columnNames = new Vector<String>();
        columnNames.addElement("NAME");
        columnNames.addElement("VALUE");
        columnNames.addElement("DATE");
        columnNames.addElement("TYPE");
        columnNames.addElement("ID");
    }


    public CenterModel(ArrayList<Money> incomesData) {
        this.initColumnNames();
        this.incomesData = incomesData;
    }

}


