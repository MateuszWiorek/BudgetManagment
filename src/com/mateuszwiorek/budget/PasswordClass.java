package com.mateuszwiorek.budget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordClass implements ActionListener{
    public static String password;
    JButton btnGet, btnLogin;
    final JFrame frame;
    final JPasswordField pfPassword;
    Users user;

    PasswordClass(Users user ){
        this.user=user;
        frame = new JFrame("Logging");
        JLabel lblPassword = new JLabel("Password:");
        pfPassword = new JPasswordField(20);
        pfPassword.setEchoChar('*');
        lblPassword.setLabelFor(pfPassword);

        btnGet = new JButton("Display Password");
        btnGet.addActionListener(this);

        btnLogin = new JButton("Sign in");
        btnLogin.addActionListener(this);
        frame.getRootPane().setDefaultButton(btnLogin);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.add(lblPassword);
        panel.add(pfPassword);
        panel.add(btnLogin);
        panel.add(btnGet);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 120);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
    static String getPassword(){
        return password;

    }

    public void hurraDialog() {
        JOptionPane.showMessageDialog(null, "Yay, You're logged in " + Users.currentLoggedUser);
    }

    public void negativeDialog() {
        JOptionPane.showMessageDialog(null, "Sorry :( Try again ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==btnGet){
            String password = new String(pfPassword.getPassword());
            JOptionPane.showMessageDialog(frame,
                    "Password is " + password);
        }
        if(source==btnLogin){
            password = new String(pfPassword.getPassword());
            if (new Users(user.getUsername(), password).comparingLoggingData(user)) {
                OptionPanel.setButtonsEnabled();
                hurraDialog();
            } else
                negativeDialog();
            frame.dispose();

        }


    }
}