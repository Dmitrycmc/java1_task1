package ru.gb.java1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SettingsWindow extends JFrame {
    SettingsWindow(final MainWindow mainWindow) {
        setTitle("Новая игра");
        setBounds(300, 300, 400, 400);

        JLabel gameModeLabel = new JLabel("Game mode:");
        final JRadioButton hvsaiRadio = new JRadioButton("Human vs. AI");
        JRadioButton hvshRadio = new JRadioButton("Human vs. human");
        hvsaiRadio.setSelected(true);
        ButtonGroup gameModeGroup = new ButtonGroup();
        gameModeGroup.add(hvsaiRadio);
        gameModeGroup.add(hvshRadio);

        JButton startButton = new JButton("Начать");

        setLayout(new GridLayout(5, 1));
        add(gameModeLabel);
        add(hvsaiRadio);
        add(hvshRadio);
        add(startButton);

        startButton.addActionListener ( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                mainWindow.startGame(hvsaiRadio.isSelected());
                setVisible(false);
            }
        });
    }
}
