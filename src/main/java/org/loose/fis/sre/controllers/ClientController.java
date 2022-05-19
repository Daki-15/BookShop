package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



import java.io.IOException;

public class ClientController {

    @FXML
    private AnchorPane listAnchor;

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
}
