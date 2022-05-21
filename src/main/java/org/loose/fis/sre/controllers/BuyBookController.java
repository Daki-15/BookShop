package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML
    private Button orderButton;

    public void handleClose() {
        Stage stage = (Stage) mainwindow.getScene().getWindow();
        stage.close();
    }

    public void orderBook()throws Exception{
        try {
            ShippingService.addShipingInfo(firstnameField.getText(),lastnameField.getText(),adressField.getText(),postalcodeField.getText());

            orderMessage.setText("Book successfully buyed!");

            firstnameField.clear();
            lastnameField.clear();
            adressField.clear();
            postalcodeField.clear();

            Stage stage = new Stage();

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("rateBookShop.fxml"));
            stage.setTitle("Book Shop");
            stage.setScene(new Scene(root, 381, 263));
            stage.show();

            //Close curent scene
            Stage curentStage = (Stage) orderButton.getScene().getWindow();
            curentStage.close();

        }catch (FieldNotCompletedException e){
            orderMessage.setText(e.getMessage());
        }
    }
}
