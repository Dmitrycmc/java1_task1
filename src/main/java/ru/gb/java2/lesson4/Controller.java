package ru.gb.java2.lesson4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    TextField messageTextField;

    @FXML
    TextArea chatTextArea;

    public void onSend(ActionEvent actionEvent) {
        String message = messageTextField.getText();
        String chatHistory = chatTextArea.getText();

        if (message.length() > 0) {
            chatTextArea.setText((chatHistory.length() > 0 ? chatHistory + "\n" : "") + message);
        }

        messageTextField.setText("");
    }
}
