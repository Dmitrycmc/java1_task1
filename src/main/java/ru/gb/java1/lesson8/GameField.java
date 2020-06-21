package ru.gb.java1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameField extends JPanel {
    private JButton[][] buttons;
    private int fieldSize;
    private int winLine;
    private GameMode gameMode;

    private static final char MARKER_O = 'O';
    private static final char MARKER_X = 'X';
    private static final char MARKER_EMPTY = ' ';

    private static char[][] field;
    private static int turnsLeft;

    private void initField() {
        field = new char[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = MARKER_EMPTY;
            }
        }
    }

    private int findMaxLine(char[][] field, char marker) {
        int line;
        int maxLine = 0;

        for (int i = 0; i < fieldSize; i++) {
            line = 1;
            for (int j = 0; j < fieldSize; j++) {
                if (j != 0 && field[i][j - 1] == field[i][j] && field[i][j] != MARKER_EMPTY) {
                    line++;
                } else {
                    line = 1;
                }
                if (line > maxLine) {
                    maxLine = line;
                }
            }
        }

        for (int j = 0; j < fieldSize; j++) {
            line = 1;
            for (int i = 0; i < fieldSize; i++) {
                if (i != 0 && field[i - 1][j] == field[i][j] && field[i][j] != MARKER_EMPTY) {
                    line++;
                } else {
                    line = 1;
                }
                if (line > maxLine) {
                    maxLine = line;
                }
            }
        }

        for (int d = 0; d < 2 * fieldSize - 1; d++) {
            line = 1;
            for (int i = Math.max(0, d - fieldSize + 1); i < Math.min(d + 1, fieldSize); i++) {
                int j = d - i;

                if (i != 0 && j != fieldSize - 1 && field[i][j] == field[i - 1][j + 1] && field[i][j] != MARKER_EMPTY) {
                    line++;
                } else {
                    line = 1;
                }
                if (line > maxLine) {
                    maxLine = line;
                }
            }
        }

        for (int d = 1 - fieldSize; d < fieldSize; d++) {
            line = 1;
            for (int i = Math.max(0, -d); i < Math.min(fieldSize - d, fieldSize); i++) {
                int j = d + i;

                if (i != 0 && j != 0 && field[i][j] == field[i - 1][j - 1] && field[i][j] != MARKER_EMPTY) {
                    line++;
                } else {
                    line = 1;
                }
                if (line > maxLine) {
                    maxLine = line;
                }
            }
        }

        return maxLine;
    }

    private boolean checkWin(char[][] field) {
        return findMaxLine(field, MARKER_O) == winLine || findMaxLine(field, MARKER_X) == winLine;
    }

    private boolean isValidTurn(int i, int j) {
        return i >= 0 && i < fieldSize && j >= 0 && j < fieldSize && field[i][j] == MARKER_EMPTY;
    }

    private void aiTurn() {
        char[][] fieldCopy = new char[fieldSize][fieldSize];

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                fieldCopy[i][j] = field[i][j];
            }
        }

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (fieldCopy[i][j] == MARKER_EMPTY) {
                    fieldCopy[i][j] = MARKER_O;
                    if (checkWin(fieldCopy)) {
                        field[i][j] = MARKER_O;
                        return;
                    }
                    fieldCopy[i][j] = MARKER_EMPTY;
                }
            }
        }

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (fieldCopy[i][j] == MARKER_EMPTY) {
                    fieldCopy[i][j] = MARKER_X;
                    if (checkWin(fieldCopy)) {
                        field[i][j] = MARKER_O;
                        return;
                    }
                    fieldCopy[i][j] = MARKER_EMPTY;
                }
            }
        }

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (fieldCopy[i][j] == MARKER_EMPTY) {
                    fieldCopy[i][j] = MARKER_X;
                    if (checkWin(fieldCopy)) {
                        field[i][j] = MARKER_O;
                        return;
                    }
                    fieldCopy[i][j] = MARKER_EMPTY;
                }
            }
        }

        int maxMaxLine = 0;
        int i0 = 0, j0 = 0;

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (fieldCopy[i][j] == MARKER_EMPTY) {
                    fieldCopy[i][j] = MARKER_O;
                    int maxLine = findMaxLine(fieldCopy, MARKER_O);
                    if (maxLine > maxMaxLine) {
                        maxMaxLine = maxLine;
                        i0 = i;
                        j0 = j;
                    }
                    fieldCopy[i][j] = MARKER_EMPTY;
                }
            }
        }

        while (!isValidTurn(i0, j0)) {
            i0 = (int) Math.floor(Math.random() * fieldSize);
            j0 = (int) Math.floor(Math.random() * fieldSize);
        }

        field[i0][j0] = MARKER_O;
    }

    private void refreshField() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                buttons[i][j].setText("" + field[i][j]);
            }
        }
    }

    private void gameOver(String result) {
        for (JButton[] buttonsLine : buttons) {
            for (JButton button : buttonsLine) {
                for (ActionListener al : button.getActionListeners()) {
                    button.removeActionListener(al);
                }
            }
        }
        JOptionPane.showMessageDialog(this, result);
    }

    public void startGame(GameMode gameMode, final int fieldSize, int winLine) {
        this.fieldSize = fieldSize;
        this.winLine = winLine;
        this.gameMode = gameMode;
        turnsLeft = fieldSize * fieldSize;

        initField();

        setLayout(new GridLayout(fieldSize, fieldSize));
        removeAll();
        buttons = new JButton[fieldSize][fieldSize];

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                buttons[i][j] = new JButton();
                add(buttons[i][j]);
                final int finalI = i;
                final int finalJ = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (field[finalI][finalJ] == MARKER_EMPTY) {
                            field[finalI][finalJ] = MARKER_X;
                            refreshField();
                            turnsLeft--;
                            if (checkWin(field)) {
                                gameOver("You win");
                                return;
                            }
                            if (turnsLeft == 0) {
                                gameOver("Draw");
                                return;
                            }
                            aiTurn();
                            refreshField();
                            turnsLeft--;
                            if (checkWin(field)) {
                                gameOver("You loose");
                                return;
                            }
                            if (turnsLeft == 0) {
                                gameOver("Draw");
                                return;
                            }
                        }
                    }
                });
            }
        }
    }
}
