package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Book;
import org.loose.fis.sre.services.BookService;

import java.net.URL;
import java.util.ResourceBundle;

public class ListElementController implements Initializable {
    @FXML
    private TableView<Book> bookTabel;
    @FXML
    private TableColumn<Book, String> bookName;
    @FXML
    private TableColumn<Book, String> authorName;
    @FXML
    private TableColumn<Book, String> bookType;
    @FXML
    private TableColumn<Book, String> publishingHouse;
    @FXML
    private TableColumn<Book, Float> bookPrice;

    private final ObjectRepository<Book> books = BookService.getBookRepository();

    ObservableList<Book> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Book book : books.find()){
            oblist.add(new Book(
                    book.getBookName(), book.getAuthorName(), book.getBookType(), book.getPublishingHouse(),book.getBookPrice()
                    ));
        }

        bookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        //bookName.setCellValueFactory(new PropertyValueFactory<>("author"));
        //bookName.setCellValueFactory(new PropertyValueFactory<>("type"));
        //bookName.setCellValueFactory(new PropertyValueFactory<>("publishingHouse"));
        //bookName.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
