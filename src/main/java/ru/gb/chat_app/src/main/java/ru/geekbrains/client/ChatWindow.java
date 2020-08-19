package ru.geekbrains.client;

import javax.swing.*;
import java.awt.*;
import java.io.*;

class ChatWindow extends JFrame {
    static final int LAST_MESSAGES = 100;
    static final String FILE_NAME = "generated_files/chat-history.txt";

    private Client client;
    private JTextArea chatTextArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(chatTextArea);
    private JPanel messagePanel = new JPanel();
    private JTextField messageTextField = new JTextField("");
    private JButton sendButton = new JButton("Send");
    JMenuBar menuBar = new JMenuBar();
    JMenu settingsMenu = new JMenu ( "Settings" );
    JMenuItem changeNickMenuItem = new JMenuItem ( "Change nick" );

    private int getFileLinesCount(String fileName) {
        int cnt = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while (line != null) {
                cnt++;
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cnt;
    }

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
            try {
                PrintWriter ps = new PrintWriter(new FileOutputStream(
                        new File(FILE_NAME),
                        true));
                ps.write(message + '\n');
                ps.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            chatTextArea.setText( message + (chatHistory.equals("") ? "" : '\n') + chatHistory );
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

        try {
            int linesCount = getFileLinesCount(FILE_NAME);
            int linesToSkip = linesCount > LAST_MESSAGES ? linesCount - LAST_MESSAGES : 0;
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                if (linesToSkip == 0) {
                    sb.insert(0, line + '\n');
                } else {
                    linesToSkip--;
                }
                line = br.readLine();
            }
            chatTextArea.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(messagePanel, BorderLayout.SOUTH);
        messagePanel.setLayout(new GridLayout(1, 2, 0, 5));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        messagePanel.add(messageTextField);
        messageTextField.addActionListener(e -> submit());

        messagePanel.add(sendButton);
        sendButton.addActionListener(e -> submit());

        changeNickMenuItem.addActionListener(e -> changeNick());
        settingsMenu.add(changeNickMenuItem);
        menuBar.add(settingsMenu);
        add(menuBar, BorderLayout.NORTH);

        setVisible(true);
        setEnabled(false);

        new AuthWindow(client, this);

        setEnabled(true);
        System.out.println("Аутентификация прошла успешно!");

        new Thread(() -> {
            while (true) {
                String answer = client.waitForAnswer();
                receive(answer);
            }
        }).start();
    }

    private void changeNick() {
        this.setEnabled(false);
        new ChangeNickWindow(client, this);
    }
}
