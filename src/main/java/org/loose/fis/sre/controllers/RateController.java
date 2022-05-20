package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;


public class RateController {
    @FXML
    private Rating rateStars;
    @FXML
    private Button cancelButton;

    public void rateShop(javafx.event.ActionEvent event) {
        System.out.println("Your rate is: " + rateStars.getRating());
    }

    public void handleClose() {
        Stage curentStage = (Stage) cancelButton.getScene().getWindow();
        curentStage.close();
    }
}
