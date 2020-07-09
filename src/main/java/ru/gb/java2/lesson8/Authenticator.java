package ru.gb.java2.lesson8;

import java.util.ArrayList;
import java.util.Arrays;

public class Authenticator {
    public static ArrayList<User> users = new ArrayList<>(Arrays.asList(
            new User("Dmitry", "123456"),
            new User("Alexander", "777")
    ));

    public static boolean auth(String login, String password) {
        for (User user: users) {
            if (user.login.equals(login) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    static class User {
        private String login;
        private String password;

        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }
    }
}
