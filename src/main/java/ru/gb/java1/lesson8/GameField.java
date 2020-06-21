package ru.gb.java1.lesson8;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    private JButton[][] buttons;

    public void startGame(GameMode gameMode, int fieldSize, int winLine) {
        setLayout(new GridLayout(fieldSize, fieldSize));
        removeAll();
        buttons = new JButton[fieldSize][fieldSize];

        for (JButton[] buttonsLine: buttons) {
            for (JButton button: buttonsLine) {
                button = new JButton();
                add(button);
            }
        }
    }
}
