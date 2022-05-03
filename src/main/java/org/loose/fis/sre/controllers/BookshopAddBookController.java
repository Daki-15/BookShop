package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.BookNameAlreadyExistsException;
import org.loose.fis.sre.exceptions.BookPriceException;
import org.loose.fis.sre.exceptions.FieldNotCompletedException;
import org.loose.fis.sre.services.BookService;

import java.io.IOException;

public class BookshopAddBookController {

    @FXML
    private TextField bookAuthor;

    @FXML
    private TextField bookName;

    @FXML
    private TextField bookPrice;

    @FXML
    private TextField bookPublishingHouse;

    @FXML
    private TextField bookType;

    @FXML
    private Text addBookMessage;

    @FXML
    void addBook() {
        try {
            if (BookService.checkIfBookPriceIsFloat(bookPrice.getText())) {
                BookService.addBook(bookName.getText(), bookAuthor.getText(), bookType.getText(), bookPublishingHouse.getText(), Float.parseFloat(bookPrice.getText()));
            } else {
                throw new BookPriceException();
            }

            addBookMessage.setText("Book successfully added!");

            bookName.clear();
            bookAuthor.clear();
            bookType.clear();
            bookPublishingHouse.clear();
            bookPrice.clear();
        } catch (BookNameAlreadyExistsException | BookPriceException | FieldNotCompletedException e) {
            addBookMessage.setText(e.getMessage());
        }
    }

    public void backToBookShopPage(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getClassLoader().getResource("bookshop.fxml"));
        Parent viewUserLogin = Loader.load();
        Scene loginScene = new Scene(viewUserLogin, 834, 475);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
}
