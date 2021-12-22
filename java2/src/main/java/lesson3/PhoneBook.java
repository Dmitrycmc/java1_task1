package lesson3;

import java.util.HashMap;
import java.util.LinkedList;

/*
    2. Написать простой класс ТелефонныйСправочник,
    который хранит в себе список фамилий и телефонных номеров.
    В этот телефонный справочник с помощью метода add() можно добавлять записи.
    С помощью метода get() искать номер телефона по фамилии. Следует учесть,
    что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
    тогда при запросе такой фамилии должны выводиться все телефоны.
*/
public class PhoneBook {
    private HashMap<String, LinkedList<String>> mapLastNameToPhones = new HashMap<>();

    public void add(String lastName, String phone) {
        if (mapLastNameToPhones.containsKey(lastName)) {
            LinkedList<String> list = mapLastNameToPhones.get(lastName);
            list.add(phone);
            mapLastNameToPhones.put(lastName, list);
        } else {
            LinkedList<String> list = new LinkedList<>();
            list.add(phone);
            mapLastNameToPhones.put(lastName, list);
        }
    }

    public LinkedList<String> get(String lastName) {
        return mapLastNameToPhones.get(lastName);
    }

    @Override
    public String toString() {
        return mapLastNameToPhones.toString();
    }
}
