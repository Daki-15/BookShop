package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.FieldNotCompletedException;
import org.loose.fis.sre.model.Book;
import org.loose.fis.sre.services.BookService;

import java.io.IOException;
import java.util.Objects;

public class BookshopDeleteBookController {

    @FXML
    private Text deleteBookMessage;

    @FXML
    private Button backButton;

    @FXML
    private TextField bookNameField;

    public void backToBookShopPage(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getClassLoader().getResource("bookshop.fxml"));
        Parent viewUserLogin = Loader.load();
        Scene loginScene = new Scene(viewUserLogin, 834, 476);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    @FXML
    void deleteBook() {
        try {
            BookService.checkBookNameFieldIsCompleted(bookNameField.getText());

            for (Book book : BookService.getBookRepository().find()) {
                if (Objects.equals(bookNameField.getText(), book.getBookName())) {
                    BookService.getBookRepository().remove(book);
                    deleteBookMessage.setText("Book " + bookNameField.getText() + " successfully deleted!");
                } else if (!Objects.equals(bookNameField.getText(), book.getBookName())) {
                    deleteBookMessage.setText("Book with the name " + bookNameField.getText() + " does not exist!");
                }
            }
            bookNameField.clear();
        } catch (FieldNotCompletedException e) {
            deleteBookMessage.setText(e.getMessage());
        }
    }
}

