package ru.gb.java1.lesson4;

import java.util.Scanner;

public class MainClass {
    private static Scanner scanner = new Scanner(System.in);

    private static final int SIZE = 3;
    private static final int WIN_LINE = 3;
    private static final char MARKER_O = 'O';
    private static final char MARKER_X = 'X';
    private static final char MARKER_EMPTY = '.';

    private static char[][] field;
    private static int turnsLeft = SIZE * SIZE;

    public static void main(String[] args) {
        initField();
        printField();
        while (true) {
            userTurn();
            turnsLeft--;
            printField();
            if (checkWin(field)) {
                System.out.println("You win!");
                return;
            }
            if (turnsLeft == 0) {
                System.out.println("Draw!");
                return;
            }

            aiTurn();
            turnsLeft--;
            printField();
            if (checkWin(field)) {
                System.out.println("You loss!");
                return;
            }
            if (turnsLeft == 0) {
                System.out.println("Draw!");
                return;
            }
        }
    }

    private static void initField() {
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = MARKER_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.print("  ");
        for (int j = 0; j < SIZE; j++) {
            System.out.print((j + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int findMaxLine(char[][] field, char marker) {
        int line;
        int maxLine = 0;

        for (int i = 0; i < SIZE; i++) {
            line = 1;
            for (int j = 0; j < SIZE; j++) {
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

        for (int j = 0; j < SIZE; j++) {
            line = 1;
            for (int i = 0; i < SIZE; i++) {
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

        for (int d = 0; d < 2 * SIZE - 1; d++) {
            line = 1;
            for (int i = Math.max(0, d - SIZE + 1); i < Math.min(d + 1, SIZE); i++) {
                int j = d - i;

                if (i != 0 && j != SIZE - 1 && field[i][j] == field[i - 1][j + 1] && field[i][j] != MARKER_EMPTY) {
                    line++;
                } else {
                    line = 1;
                }
                if (line > maxLine) {
                    maxLine = line;
                }
            }
        }

        for (int d = 1 - SIZE; d < SIZE; d++) {
            line = 1;
            for (int i = Math.max(0, -d); i < Math.min(SIZE - d, SIZE); i++) {
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

    private static boolean checkWin(char[][] field) {
        return findMaxLine(field, MARKER_O) == WIN_LINE || findMaxLine(field, MARKER_X) == WIN_LINE;
    }

    private static boolean isValidTurn(int i, int j) {
        return i >= 0 && i < SIZE && j >= 0 && j < SIZE && field[i][j] == MARKER_EMPTY;
    }

    private static void userTurn() {
        int i, j;
        do {
            System.out.println("Make you turn: ");
            i = scanner.nextInt();
            j = scanner.nextInt();
        } while (!isValidTurn(i - 1, j - 1));
        field[i - 1][j - 1] = MARKER_X;
    }

    private static void aiTurn() {
        char[][] fieldCopy = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                fieldCopy[i][j] = field[i][j];
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
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

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
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

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
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

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
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
            i0 = (int)Math.floor(Math.random() * SIZE);
            j0 = (int)Math.floor(Math.random() * SIZE);
        }

        field[i0][j0] = MARKER_O;
    }
}


