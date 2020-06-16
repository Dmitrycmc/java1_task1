package ru.gb.java1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainWindow extends JFrame {
    MainWindow() {
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);

        JPanel buttonsPanel = new JPanel();
        JButton startButton = new JButton("Начать игру");
        JButton exitButton = new JButton("Закончить игру");

        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.add(startButton);
        buttonsPanel.add(exitButton);

        setLayout(new BorderLayout());
        add(buttonsPanel, BorderLayout.SOUTH);

        final SettingsWindow settingsWindow = new SettingsWindow(this);

        startButton.addActionListener ( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                settingsWindow.setVisible(true);
            }
        });


        setVisible(true);
    }

    void startGame(boolean gameMode) {
        System.out.println(gameMode);
    }
}
