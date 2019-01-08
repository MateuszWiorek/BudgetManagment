package com.mateuszwiorek.budget;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ApplicationsFrame extends JFrame  {

    private UsersPanel usersPanel;
    private OptionPanel optionPanel;
    private JLabel header, footer;
    MoneyList bla = new MoneyList();
    public static JTable resultsTable;

    public static BorderLayout borderLayout;

    ApplicationsFrame(){
        super("Budget Management");
        setBackground(Color.LIGHT_GRAY);
        borderLayout = new BorderLayout();
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(borderLayout);
        usersPanel = new UsersPanel();
        optionPanel = new OptionPanel();
        resultsTable = new JTable();
        resultsTable.setModel(new CenterModel((ArrayList<Money>)bla.moneyList));
        header = new JLabel("App for home budget management" );
        add(header);
        add(resultsTable);
        add(usersPanel);
        add(optionPanel);
        borderLayout.addLayoutComponent(header, BorderLayout.NORTH);
        borderLayout.addLayoutComponent(optionPanel,BorderLayout.WEST);
        borderLayout.addLayoutComponent(usersPanel, BorderLayout.EAST);
        borderLayout.addLayoutComponent(resultsTable,BorderLayout.CENTER);

        pack();
        setVisible(true);

    }
}
