package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.FieldNotCompletedException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;

import java.security.cert.Extension;
import java.util.Objects;

public class RegistrationController {

    @FXML
    private Text registrationMessage;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private ChoiceBox<String> role;

    @FXML
    private TextField addressField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button registerButton;

    private Button logInButton;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Bookshop");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), role.getValue(),
                    phoneField.getText(), addressField.getText(), nameField.getText());

            registrationMessage.setText("Account created successfully!");
            usernameField.clear();
            passwordField.clear();
            phoneField.clear();
            addressField.clear();
            nameField.clear();
        } catch (UsernameAlreadyExistsException | FieldNotCompletedException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    public void openLogInScene() throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));

            Stage window = (Stage) logInButton.getScene().getWindow();
            window.setScene(new Scene(root, 600, 400));
        }
        catch (Exception e){
            System.out.println("Can not load \"Log In\" scene!");
        }
    }
}
