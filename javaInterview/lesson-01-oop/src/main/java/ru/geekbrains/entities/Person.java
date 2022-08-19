package ru.geekbrains.entities;

import java.util.Objects;

public class Person {
    public static class Builder {
        private final Person person = new Person();

        public Builder firstName(String value) {
            person.firstName = value;
            return this;
        }

        public Builder lastName(String value) {
            person.lastName = value;
            return this;
        }

        public Builder middleName(String value) {
            person.middleName = value;
            return this;
        }

        public Builder country(String value) {
            person.country = value;
            return this;
        }

        public Builder address(String value) {
            person.address = value;
            return this;
        }

        public Builder phone(String value) {
            person.phone = value;
            return this;
        }

        public Builder gender(String value) {
            person.gender = value;
            return this;
        }

        public Builder age(int value) {
            person.age = value;
            return this;
        }

        public Person build() {
            return person;
        }
    }

    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private String gender;
    private int age;

    private Person() {
        // use Person.builder() instead
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public static Person.Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(middleName, person.middleName) && Objects.equals(country, person.country) && Objects.equals(address, person.address) && Objects.equals(phone, person.phone) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName, country, address, phone, gender, age);
    }

    @Override
    public String toString() {
        return "Person{\n" +
                "    firstName='" + firstName + "\',\n" +
                "    lastName='" + lastName + "\',\n" +
                "    middleName='" + middleName + "\',\n" +
                "    country='" + country + "\',\n" +
                "    address='" + address + "\',\n" +
                "    phone='" + phone + "\',\n" +
                "    gender='" + gender + "\',\n" +
                "    age=" + age + "\n" +
                "}";
    }
}
