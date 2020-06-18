package ru.gb.java1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainWindow extends JFrame {
    private JPanel buttonsPanel = new JPanel();
    ;

    private JButton startButton = new JButton("<html><body><b style='font-size: 1.5em'>Начать игру</b></body></html>");
    private JButton exitButton = new JButton("Выйти");

    private SettingsWindow settingsWindow = new SettingsWindow(this);

    MainWindow() {
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);

        setLayout(new BorderLayout());
        add(buttonsPanel, BorderLayout.SOUTH);

        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.add(startButton);
        buttonsPanel.add(exitButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsWindow.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    void startGame(GameMode gameMode, int fieldSize, int winLine) {
        System.out.println(gameMode + " " + fieldSize + " " + winLine);
    }
}
