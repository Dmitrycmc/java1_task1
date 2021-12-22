package lesson2;

public class MainClass {
    final static int SIZE = 4;

    public static void main(String[] args) {
        try {
            System.out.println(calculateSumOfArray(new String[][]{
                    {"1", "2", "4", "4"},
                    {"1", "2", "4", "4"},
                    {"1", "2", "4", "4"},
                    {"1", "2", "4", "4"},
                    {"1", "2", "4", "4"},
            }));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(calculateSumOfArray(new String[][]{
                    {"1", "2", "4", "4"},
                    {"1", "2", "4", "4"},
                    {"1", "2", "4", "4"},
                    {"1", "2", "4", "4"},
            }));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(calculateSumOfArray(new String[][]{
                    {"1", "2", "4", "4"},
                    {"1", "2", "4", "4"},
                    {"1", "2", "4bb", "4"},
                    {"1", "2", "4", "4", "8"},
            }));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(calculateSumOfArray(new String[][]{
                    {"1", "2", "4", "4"},
                    {"1", "2", "4", "4"},
                    {"1", "2", "4", "4%%"},
                    {"1", "2", "4", "4"},
            }));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int calculateSumOfArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != SIZE) {
            throw new MyArraySizeException();
        }
        for (String[] line: array) {
            if (line.length != SIZE) {
                throw new MyArraySizeException();
            }
        }
        int sum = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                try {
                    int number = Integer.parseInt(array[i][j]);
                    sum += number;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        return sum;
    }
}


