package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.BookNameAlreadyExistsException;
import org.loose.fis.sre.exceptions.BookPriceException;
import org.loose.fis.sre.exceptions.FieldNotCompletedException;
import org.loose.fis.sre.model.Book;
import org.loose.fis.sre.services.BookService;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BookshopEditBookController implements Initializable {

    @FXML
    private Text searchedBookMessage;

    @FXML
    private Text editBookMessage;

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
    private Button editBookButton;

    @FXML
    private HBox hboxEdit;

    @FXML
    private HBox hboxSearch;

    @FXML
    private TextField searchBookName;

    @FXML
    private Button searchBookButton;

    private static Book existingBook;

    @FXML
    void editBook() {
        BookService.getBookRepository().remove(existingBook);

        try {
            if (BookService.checkIfBookPriceIsFloat(bookPrice.getText())) {
                BookService.addBook(bookName.getText(), bookAuthor.getText(), bookType.getText(), bookPublishingHouse.getText(), Float.parseFloat(bookPrice.getText()));
            } else {
                throw new BookPriceException();
            }

            searchBookName.clear();
            hboxSearch.setVisible(true);
            hboxEdit.setVisible(false);
            searchedBookMessage.setVisible(true);
            editBookMessage.setVisible(false);
            searchedBookMessage.setText("Book successfully updated!");

            bookName.clear();
            bookAuthor.clear();
            bookType.clear();
            bookPublishingHouse.clear();
            bookPrice.clear();

        } catch (BookNameAlreadyExistsException | BookPriceException | FieldNotCompletedException e) {
            editBookMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void searchBook() {

        int k = 0;
        for (Book book : BookService.getBookRepository().find()) {
            if (Objects.equals(searchBookName.getText(), book.getBookName())) {
                existingBook = book;
                k = 1;
            }
        }
        if (existingBook != null) {
            hboxSearch.setVisible(false);
            searchedBookMessage.setVisible(false);
            hboxEdit.setVisible(true);
            editBookMessage.setVisible(true);
        }

        if (searchBookName.getText().equals("")) {
            hboxSearch.setVisible(true);
            searchedBookMessage.setVisible(true);
            searchedBookMessage.setText("Please complete all fields!");
            hboxEdit.setVisible(false);
            editBookMessage.setVisible(false);
        }

        if (k == 0 && (!searchBookName.getText().equals(""))) {
            hboxSearch.setVisible(true);
            searchedBookMessage.setVisible(true);
            searchedBookMessage.setText("Book with the name " + searchBookName.getText() + " does not exist!");
            hboxEdit.setVisible(false);
            editBookMessage.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hboxSearch.setVisible(true);
        hboxEdit.setVisible(false);
    }

    @FXML
    public void backToBookShopPage(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getClassLoader().getResource("bookshop.fxml"));
        Parent viewUserLogin = Loader.load();
        Scene loginScene = new Scene(viewUserLogin, 834, 477);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
}
