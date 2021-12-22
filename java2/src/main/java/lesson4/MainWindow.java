package lesson4;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class MainWindow extends JFrame {
    JTextArea chatTextArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(chatTextArea);
    JPanel messagePanel = new JPanel();
    JTextField messageTextField = new JTextField("");
    JButton sendButton = new JButton("Send");

    private void send() {
        String chatHistory = chatTextArea.getText();
        String msg = messageTextField.getText().trim();
        if (!msg.equals("")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String time = dtf.format(now);

            chatTextArea.setText(chatHistory + (chatHistory.equals("") ? "" : '\n') + time + " You: " + msg);
            messageTextField.setText("");
        }
    }

    MainWindow() {
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
        messageTextField.addActionListener(e -> send());

        messagePanel.add(sendButton);
        sendButton.addActionListener(e -> send());

        setVisible(true);
    }
}
