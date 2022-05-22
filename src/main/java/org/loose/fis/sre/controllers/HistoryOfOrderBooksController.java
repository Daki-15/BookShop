package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.SoldBook;
import org.loose.fis.sre.services.HistoryOfOrderService;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryOfOrderBooksController implements Initializable {
    private final ObjectRepository<SoldBook> orderHistoryRepository = HistoryOfOrderService.getBookRepository();
    @FXML
    private TableView<SoldBook> bookHistoryTable;
    @FXML
    private TableColumn<SoldBook, String> bookName;
    @FXML
    private TableColumn<SoldBook, String> authorName;
    @FXML
    private TableColumn<SoldBook, String> bookType;
    @FXML
    private TableColumn<SoldBook, String> publishingHouse;
    @FXML
    private TableColumn<SoldBook, Float> bookPrice;

    ObservableList<SoldBook> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        bookType.setCellValueFactory(new PropertyValueFactory<>("bookType"));
        publishingHouse.setCellValueFactory(new PropertyValueFactory<>("publishingHouse"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));

        bookHistoryTable.setItems(getBook());
    }

    public ObservableList<SoldBook> getBook() {
        for (SoldBook book : orderHistoryRepository.find()) {
            oblist.add(book);
        }
        return oblist;
    }
}
