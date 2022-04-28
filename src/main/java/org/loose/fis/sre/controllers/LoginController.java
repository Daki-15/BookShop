package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {
    @FXML
    private Text logInMessage;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private ChoiceBox<String> role;

    @FXML
    private Button registerButton;

    @FXML
    private Button logInButton;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Bookshop");
    }
}
