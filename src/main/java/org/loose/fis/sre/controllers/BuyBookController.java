package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.FieldNotCompletedException;
import org.loose.fis.sre.services.ShippingService;

public class BuyBookController {
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField adressField;
    @FXML
    private TextField postalcodeField;
    @FXML
    private AnchorPane mainwindow;
    @FXML
    private Text orderMessage;

    public void handleClose() {
        Stage stage = (Stage) mainwindow.getScene().getWindow();
        stage.close();
    }

    public void orderBook(){
        try {
            ShippingService.addShipingInfo(firstnameField.getText(),lastnameField.getText(),adressField.getText(),postalcodeField.getText());

            orderMessage.setText("Book successfully buyed!");

            firstnameField.clear();
            lastnameField.clear();
            adressField.clear();
            postalcodeField.clear();

        }catch (FieldNotCompletedException e){
            orderMessage.setText(e.getMessage());
        }
    }
}
