package ru.gb.java2.lesson8.server;

import java.util.ArrayList;
import java.util.Arrays;

class Authenticator {
    private static ArrayList<User> users = new ArrayList<>(Arrays.asList(
            new User("Dmitry", "123456"),
            new User("Oleg", "0000"),
            new User("Masha", "1111"),
            new User("Alexander", "777")
    ));

    static boolean auth(String login, String password) {
        for (User user : users) {
            if (user.login.equals(login) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    static class User {
        private String login;
        private String password;

        User(String login, String password) {
            this.login = login;
            this.password = password;
        }
    }
}
