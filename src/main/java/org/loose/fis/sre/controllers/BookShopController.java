package org.loose.fis.sre.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.loose.fis.sre.model.Shipping;
import org.loose.fis.sre.services.ShippingService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookShopController implements Initializable {

    @FXML
    private Button addBookButton;

    @FXML
    private Button deleteBookButton;

    @FXML
    private Button editBookButton;

    @FXML
    private TableColumn<Shipping, String> customerFirstName;

    @FXML
    private TableColumn<Shipping, String> customerLastName;

    @FXML
    private TableColumn<Shipping, String> customerAddress;

    @FXML
    private TableView<Shipping> soldBooksTable;

    @FXML
    private Button logoutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerFirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        customerLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        soldBooksTable.setItems(ShippingService.getShippingList());
    }

    @FXML
    private void addBookAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getClassLoader().getResource("bookshop_addBook.fxml"));
        Parent viewUserLogin = Loader.load();
        Scene loginScene = new Scene(viewUserLogin, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    @FXML
    private void deleteBookAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getClassLoader().getResource("bookshop_deleteBook.fxml"));
        Parent viewUserLogin = Loader.load();
        Scene loginScene = new Scene(viewUserLogin, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    @FXML
    private void editBookAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getClassLoader().getResource("bookshop_editBook.fxml"));
        Parent viewUserLogin = Loader.load();
        Scene loginScene = new Scene(viewUserLogin, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    @FXML
    private void handleLogoutAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getClassLoader().getResource("login.fxml"));
        Parent viewUserLogin = Loader.load();
        Scene loginScene = new Scene(viewUserLogin, 610, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
}
