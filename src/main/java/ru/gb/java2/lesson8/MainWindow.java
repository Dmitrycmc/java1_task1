package ru.gb.java2.lesson8;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow(Client client) {
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setResizable(false);

        setVisible(true);
        setEnabled(false);

        new AuthWindow(this, client);
    }
}
