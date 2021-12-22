package lesson3;

import java.util.Scanner;

public class MainClass {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        game1();
        System.out.println();
        game2();
    }

    /*
        1. Написать программу, которая загадывает случайное число от 0 до 9
        и пользователю дается 3 попытки угадать это число.
        При каждой попытке компьютер должен сообщить,
        больше ли указанное пользователем число, чем загаданное, или меньше.
        После победы или проигрыша выводится запрос –
        «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
    */
    private static void game1() {
        int answer = (int) Math.floor(Math.random() * 10);
        System.out.println("Guess a number from 0 to 9:");

        for (int i = 1; i <= 3; i++) {
            System.out.println("Attempt #" + i + ":");
            int userSuggestion = scanner.nextInt();
            if (userSuggestion == answer) {
                System.out.println("Yes!");
                System.out.println("X = " + userSuggestion);
                System.out.println("You won!");
                return;
            } else {
                System.out.println("No");
                System.out.println("X" + (answer > userSuggestion ? " > " : " < ") + userSuggestion);
            }
        }

        System.out.println("You loss!");
        System.out.println("X = " + answer);
    }

    /*
        2*. Создать массив из слов
        String[] words = {"apple", "orange", "lemon", "banana", "apricot",
        "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon",
        "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
        "pear", "pepper", "pineapple", "pumpkin", "potato"}.

        При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
        сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь.
        Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
        apple – загаданное
        apricot - ответ игрока
        ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
        Для сравнения двух слов посимвольно можно пользоваться:
        String str = "apple";
        char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
        Играем до тех пор, пока игрок не отгадает слово.
        Используем только маленькие буквы.
    */
    private static void game2() {
        String[] answers = {"apple", "orange", "lemon", "banana", "apricot",
                "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon",
                "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};

        String answer = answers[(int) Math.floor(Math.random() * answers.length)];

        System.out.println("Guess a word:");

        while (true) {
            String userSuggestion = (scanner.next()).toLowerCase();
            for (int i = 0; i < 20; i++) {
                if (userSuggestion.equals(answer)) {
                    System.out.print("You won!");
                    return;
                }
                if (i >= userSuggestion.length() || i >= answer.length() || userSuggestion.charAt(i) != answer.charAt(i)) {
                    System.out.print('.');
                } else {
                    System.out.print(userSuggestion.charAt(i));
                }
            }
            System.out.println();
        }
    }
}


