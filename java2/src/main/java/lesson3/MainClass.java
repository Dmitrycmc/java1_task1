package lesson3;

import java.util.HashMap;

public class MainClass {
    public static void main(String[] args) {
        task1();

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Petrov", "84-25-36");
        phoneBook.add("Sidorov", "74-15-96");
        phoneBook.add("Ivanov", "12-25-36");
        phoneBook.add("Petrov", "94-25-36");

        System.out.println(phoneBook);
    }

    /*
        1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        Посчитать сколько раз встречается каждое слово.
    */
    private static void task1() {
        String[] arr = new String[] {
                "Кот",
                "Собака",
                "Лошадь",
                "Слон",
                "Лошадь",
                "Тигр",
                "Кот",
                "Лев",
                "Лошадь",
                "Собака",
                "Лев",
                "Жираф",
                "Слон",
                "Кот"
        };

        HashMap<String, Integer> mapStringToFrequency = new HashMap<>();

        for (String str: arr) {
            if (mapStringToFrequency.containsKey(str)) {
                mapStringToFrequency.put(str, mapStringToFrequency.get(str) + 1);
            } else {
                mapStringToFrequency.put(str, 1);
            }
        }

        System.out.println(mapStringToFrequency);
    }
}


