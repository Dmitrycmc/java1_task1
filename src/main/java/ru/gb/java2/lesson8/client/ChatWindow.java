package ru.gb.java2.lesson8.client;

import javax.swing.*;
import java.awt.*;

class ChatWindow extends JFrame {
    private Client client;
    private JTextArea chatTextArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(chatTextArea);
    private JPanel messagePanel = new JPanel();
    private JTextField messageTextField = new JTextField("");
    private JButton sendButton = new JButton("Send");

    private void submit() {
        String message = messageTextField.getText().trim();
        if (!message.equals("")) {
            client.send(message);
            messageTextField.setText("");
        }
    }

    private void receive(String message) {
        String chatHistory = chatTextArea.getText();
        if (!message.equals("")) {
            chatTextArea.setText(message + (chatHistory.equals("") ? "" : '\n') + chatHistory );
        }
    }

    ChatWindow(Client client) {
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

        new AuthWindow(client, this);

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
