package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientController {

    @FXML
    private AnchorPane listAnchor;
    @FXML
    private Button logOutButton;

    //Menu controller
    @FXML
    public void loadBookList(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("list_element_utilizator.fxml"));
        listAnchor.getChildren().setAll(pane);
    }
    @FXML
    public void loadHistoryOfOrderBooks(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("orderHistory.fxml"));
        listAnchor.getChildren().setAll(pane);
    }
    @FXML
    private void handleExitAction(ActionEvent event) throws IOException {
        System.exit(0); //Close the application
    }
    @FXML
    private void handleLogOutAction(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getClassLoader().getResource("login.fxml"));
        Parent viewUserLogin = Loader.load();
        Scene loginScene = new Scene(viewUserLogin, 610, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

}
