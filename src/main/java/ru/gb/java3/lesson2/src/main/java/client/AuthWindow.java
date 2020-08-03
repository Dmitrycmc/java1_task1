package client;

import javax.swing.*;
import java.awt.*;

class AuthWindow extends JFrame {
    private Client client;
    private String login;
    private JTextField loginField;
    private JTextField passwordField;

    private void submit() {
        login = loginField.getText();
        client.send(login + " " + passwordField.getText());
        loginField.setText("");
        passwordField.setText("");
    }

    AuthWindow(Client client, ChatWindow chatWindow) {
        this.client = client;

        setTitle("Вход");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 160);
        setResizable(false);

        setLayout(new GridLayout(4, 1));

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(1, 2));
        loginPanel.add(new JLabel("Login"));
        loginField = new JTextField();
        loginField.addActionListener(e -> submit());
        loginPanel.add(loginField);
        add(loginPanel);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new GridLayout(1, 2));
        passwordPanel.add(new JLabel("Password"));
        passwordField = new JTextField();
        passwordField.addActionListener(e -> submit());
        passwordPanel.add(passwordField);
        add(passwordPanel);

        JButton sendButton = new JButton("Sign in");
        sendButton.addActionListener(e -> submit());
        add(sendButton);

        JLabel statusLabel = new JLabel("Ожидание авторизации");
        add(statusLabel);

        setVisible(true);

        String answer;
        do {
            answer = client.waitForAnswer();
            statusLabel.setText(answer);
            repaint();
        } while (!answer.equals("Success"));

        chatWindow.setTitle("Чат: " + login);
        setVisible(false);
    }
}
