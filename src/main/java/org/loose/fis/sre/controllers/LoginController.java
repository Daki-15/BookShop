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
import org.loose.fis.sre.services.UserService;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox<String> role;

    @FXML
    private Button registerButton;

    @FXML
    private Button logInButton;

    @FXML
    private Text logInMessage;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Bookshop");
    }

    public void handleRegisterAction() throws Exception{

        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        stage.setTitle("Book Shop");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

        // get a handle to the stage
        Stage curentStage = (Stage) registerButton.getScene().getWindow();
        // do what you have to do
        curentStage.close();
    }

    public void handleLogInAction() throws Exception{
        int ok = UserService.checkForUser(usernameField.getText(), passwordField.getText(), role.getValue());

        if(ok == 0){

            logInMessage.setText("Account Log In successfully!");
            usernameField.clear();
            passwordField.clear();

            //In function the role it will be displayed different scene
            if(role.getValue() == "Client"){
                //...Client scene/window
                Stage stage = new Stage();

                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
                stage.setTitle("Client");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();

                //Close curent scene
                Stage curentStage = (Stage) registerButton.getScene().getWindow();
                curentStage.close();
            }

            if(role.getValue() == "Bookshop"){
                //...Bookshop scene/window
                Stage stage = new Stage();

                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("bookshop.fxml"));
                stage.setTitle("Book Shop");
                stage.setScene(new Scene(root, 834, 475));
                stage.show();

                //Close curent scene
                Stage curentStage = (Stage) registerButton.getScene().getWindow();
                curentStage.close();
            }
        }

        if(ok == 1){
            logInMessage.setText("Your password or role is wrong! Try again");
        }
        if(ok == 2){
            logInMessage.setText("Account dosen't exist !");
        }
    }
}
