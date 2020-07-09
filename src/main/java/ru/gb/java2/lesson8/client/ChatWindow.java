package ru.gb.java2.lesson8.client;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatWindow extends JFrame {
    Client client;
    JTextArea chatTextArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(chatTextArea);
    JPanel messagePanel = new JPanel();
    JTextField messageTextField = new JTextField("");
    JButton sendButton = new JButton("Send");

    private void submit() {
        String msg = messageTextField.getText().trim();
        if (!msg.equals("")) {
            client.send(msg);
            messageTextField.setText("");
        }
    }

    private void receive(String message) {
        String chatHistory = chatTextArea.getText();
        if (!message.equals("")) {
            chatTextArea.setText(chatHistory + (chatHistory.equals("") ? "" : '\n') + message);
        }
    }

    public ChatWindow(Client client) {
        this.client = client;
        setTitle("Чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setResizable(false);

        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);
        chatTextArea.setEditable(false);
        chatTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        chatTextArea.setLineWrap(true);
        chatTextArea.setWrapStyleWord(true);


        add(messagePanel, BorderLayout.SOUTH);
        messagePanel.setLayout(new GridLayout(1, 2, 0, 5));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        messagePanel.add(messageTextField);
        messageTextField.addActionListener(e -> submit());

        messagePanel.add(sendButton);
        sendButton.addActionListener(e -> submit());

        setVisible(true);
        setEnabled(false);

        new AuthWindow(client);

        setEnabled(true);
        System.out.println("Аутенфикация прошла успешно!");

        new Thread(() -> {
            while (true) {
                String answer = client.waitForAnswer();
                receive(answer);
            }
        }).start();
    }
}
