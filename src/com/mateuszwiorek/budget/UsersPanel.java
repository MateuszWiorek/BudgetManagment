package com.mateuszwiorek.budget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersPanel extends JPanel implements ActionListener {

    private String questionAboutPassword;

    private JTextField password;

    UsersList usersList = new UsersList();

    int rozmiar = usersList.usersList.size();

    private JButton[] usersButtonList = new JButton[rozmiar];

    public UsersPanel() {
        GridLayout usersGrid = new GridLayout(0, 1);
        setLayout(usersGrid);
        for (int i = 0; i < usersList.usersList.size(); i++) {
            usersButtonList[i] = (new JButton(usersList.usersList.get(i).getUsername()));
            usersButtonList[i].addActionListener(this);
            add(usersButtonList[i]);
            usersButtonList[i].setBackground(Color.cyan);
        }
        usersButtonList[0].setBackground(Color.cyan);
        usersButtonList[1].setBackground(Color.BLACK);
        usersButtonList[2].setBackground(Color.green);
        usersButtonList[3].setBackground(Color.pink);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < usersButtonList.length; i++) {
            if (source == usersButtonList[i]) {
                new PasswordClass(usersList.usersList.get(i));
                break;
            }


            repaint();
        }
    }
}
