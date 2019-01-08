package com.mateuszwiorek.budget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OptionPanel extends JPanel implements ActionListener {
    JTable tabela;

     static JButton addToDatabase, viewAllIncomes, viewAllExpenses, searchIncomesByName, searchIncomesByValue, searchIncomesByDate, searchExpensesByName, searchExpensesByValue, searchExpensesByDate, viewAllOrderedByDate, drawDiagram, viewAll, delete;


    static JButton[] options = {addToDatabase, viewAllIncomes, viewAllExpenses, searchIncomesByName, searchIncomesByValue, searchIncomesByDate, searchExpensesByName, searchExpensesByValue, searchExpensesByDate, viewAllOrderedByDate, drawDiagram, viewAll, delete};

    String[] optionNames={"Add", "View incomes", "View expenses", "Search incomes(name)", "Search incomes(value)", "Search incomes(date)",
            "Search expenses(date)", "Search expenses(value)", "Search expenses(date)", "View all (date range)", "View chart", "View all", "Delete"};
    String value;
    String dayStart;
    String dayEnd;
    String response1, response2, response3, response4;
    JTextField valueInput, dayStartInput, dayEndInput, name, valueAdding, tabletype, day;
    OptionPanel(){
        for (int i=0; i<13;i++){
            options[i]=new JButton(optionNames[i]);
            options[i].addActionListener(this);
            options[i].setEnabled(false);
            options[i].setBackground(Color.orange);
        }
        setLayout(new GridLayout(0,1));
        for (int i=0;i<13;i++){
            add(options[i]);
        }
        setVisible(true);
    }

    private void valueDialog() {
        value = JOptionPane.showInputDialog(null, "Enter value");
        valueInput = new JTextField(value, 10);
    }

    private void dateDialog()  {
        dayStart = JOptionPane.showInputDialog(null, "Enter date in format  YYYY/MM/DD");
        dayStartInput = new JTextField(dayStart, 10);
        dayEnd = JOptionPane.showInputDialog(null, "Enter date in format  YYYY/MM/DD");
        dayEndInput = new JTextField(dayEnd, 10);

        }

    private void askForContent() {
        response1 = JOptionPane.showInputDialog(null, "Enter name");
        name = new JTextField(response1, 20);
        response2 = JOptionPane.showInputDialog(null, "Enter value");
        valueAdding = new JTextField(response2, 20);
        response3 = JOptionPane.showInputDialog(null, "Enter date in format  YYYY/MM/DD");
        day = new JTextField(response3, 10);
        response4 = JOptionPane.showInputDialog(null,"Enter type (incomes/expenses)");
        tabletype = new JTextField(response4, 8);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        Object source = e.getSource();
        if (source==options[0]){
            askForContent();
            new BudgetDAO().addToDatabase(response1,Integer.parseInt(response2), dateFormatter(response3) ,response4);
            ApplicationsFrame.resultsTable.setModel(new CenterModel((ArrayList<Money>) new BudgetDAO().viewAll()));

        }
        else if(source==options[1]){
            ApplicationsFrame.resultsTable.setModel(new CenterModel((ArrayList<Money>) new BudgetDAO().viewAll("incomes")));
            tabela=new JTable(new CenterModel((ArrayList<Money>) new BudgetDAO().viewAll("incomes")));
        }
        else if(source==options[2]){
            ApplicationsFrame.resultsTable.setModel(new CenterModel((ArrayList<Money>) new BudgetDAO().viewAll("expenses")));
            tabela=new JTable(new CenterModel((ArrayList<Money>) new BudgetDAO().viewAll("expenses")));
        }
        else if(source==options[3]){
            valueDialog();
            ApplicationsFrame.resultsTable.setModel(new CenterModel((ArrayList<Money>) new BudgetDAO().searchByName("incomes",value)));
        }
        else if(source==options[4]){
            valueDialog();
            ApplicationsFrame.resultsTable.setModel(new CenterModel((ArrayList<Money>) new BudgetDAO().searchByValue("incomes", Integer.parseInt(value))));
        }
        else if(source==options[5]){
            valueDialog();
            ApplicationsFrame.resultsTable.setModel(new CenterModel((ArrayList<Money>) new BudgetDAO().searchByDate("incomes", dateFormatter(value))));

        }
        else if(source==options[6]){
            valueDialog();
            ApplicationsFrame.resultsTable.setModel(new CenterModel((ArrayList<Money>) new BudgetDAO().searchByName("expenses", value)));
        }
        else if(source==options[7]){
            valueDialog();
            ApplicationsFrame.resultsTable.setModel(new CenterModel((ArrayList<Money>)new BudgetDAO().searchByValue("expenses", Integer.parseInt(value))));
        }
        else if(source==options[8]){
            valueDialog();
            ApplicationsFrame.resultsTable.setModel(new CenterModel((ArrayList<Money>) new BudgetDAO().searchByDate("expenses", dateFormatter(value))));

        }
        else if(source==options[9]){
            dateDialog();
            ApplicationsFrame.resultsTable.setModel(new CenterModel((ArrayList<Money>) new BudgetDAO().viewAllOrderedByDate(dateFormatter(dayStart), dateFormatter(dayEnd))));
        }
        else if(source==options[10]){
            My3DCategoryChart frame = new My3DCategoryChart("Chart for user: "+Users.currentLoggedUser);
            frame.pack();
            frame.revalidate();
            frame.setVisible(true);
        }
        else if(source==options[11]){
            ApplicationsFrame.resultsTable.setModel(new CenterModel((ArrayList<Money>) new BudgetDAO().viewAll()));
        }
        else if(source==options[12]){
            int indeks = ApplicationsFrame.resultsTable.getSelectedRow();
            int idToDelete = Integer.parseInt(ApplicationsFrame.resultsTable.getValueAt(indeks,4).toString());
            String tableType =  ApplicationsFrame.resultsTable.getValueAt(indeks, 3).toString();

                new BudgetDAO().delete(tableType, idToDelete);
                ApplicationsFrame.resultsTable.updateUI();

        }
    repaint();
    }

    public static void setButtonsEnabled(){
        for (int i=0; i<13;i++)
            options[i].setEnabled(true);
    }

    public static java.sql.Date dateFormatter(String inputDate){
        long dateLong;
        java.util.Date dateUtil= null;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try {
            dateUtil = formatter.parse(inputDate);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        dateLong= dateUtil.getTime();
        java.util.Date utilDate = new java.util.Date(dateLong);
        java.sql.Date finalDate = new java.sql.Date(utilDate.getTime());
    return finalDate;
    }
}

