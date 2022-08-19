package ru.geekbrains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.entities.Person;

public class Task1 {
    @Test
    void personTest() {
        String firstName = "Igor";
        String lastName = "Ivanov";

        Person person1 = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();

        Person person2 = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();

        Person person3 = Person.builder()
                .firstName(firstName)
                .build();

        Person person4 = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(23)
                .build();

        Person person5 = Person.builder()
                .firstName(lastName)
                .firstName(firstName)
                .build();

        Person person6 = Person.builder()
                .firstName(lastName)
                .firstName(firstName)
                .age(23)
                .address("New York City")
                .country("USA")
                .gender("Male")
                .phone("+123456789")
                .build();

        Assertions.assertTrue(person1.equals(person2));
        Assertions.assertFalse(person1.equals(person3));
        Assertions.assertFalse(person1.equals(person4));
        Assertions.assertFalse(person1.equals(person5));
        Assertions.assertTrue(person3.equals(person5));
        Assertions.assertFalse(person1.equals(person6));
        Assertions.assertEquals(person1.getFirstName(), firstName);
        Assertions.assertEquals(person1.getLastName(), lastName);

        System.out.println(person6);
    }
}
