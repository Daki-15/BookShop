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
import org.loose.fis.sre.model.Shipping;
import org.loose.fis.sre.services.BookService;
import org.loose.fis.sre.services.ShippingService;


import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HistoryOfOrderBooksController implements Initializable {
    private final ObjectRepository<Book> bookRepository = BookService.getBookRepository();
    private final ObjectRepository<Shipping> shippingRepository = ShippingService.getShippingRepository();

    @FXML
    private TableView<Book> bookHistoryTable;
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

    private String curentUser = LoginController.getCurentUser();

    ObservableList<Book> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        bookType.setCellValueFactory(new PropertyValueFactory<>("bookType"));
        publishingHouse.setCellValueFactory(new PropertyValueFactory<>("publishingHouse"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));

        bookHistoryTable.setItems(getBook());

        System.out.println("User name is " + curentUser);
    }

    public ObservableList<Book> getBook() {
        for(Shipping shipping : shippingRepository.find()){
            if (Objects.equals(curentUser, shipping.getUserName()))
                oblist.add(book);
        }
        return oblist;
    }
}
