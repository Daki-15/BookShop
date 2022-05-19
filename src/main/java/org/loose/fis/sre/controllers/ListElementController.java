package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Book;
import org.loose.fis.sre.services.BookService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ListElementController implements Initializable {

    private final ObjectRepository<Book> bookRepository = BookService.getBookRepository();
    @FXML
    private TableView<Book> bookTable;
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

    ObservableList<Book> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        bookType.setCellValueFactory(new PropertyValueFactory<>("bookType"));
        publishingHouse.setCellValueFactory(new PropertyValueFactory<>("publishingHouse"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
        addButtonToTable();

        bookTable.setItems(getBook());
    }

    public ObservableList<Book> getBook() {
        for(Book book : bookRepository.find()){
            oblist.add(book);
        }
        return oblist;
    }

        private void addButtonToTable(){
            TableColumn<Book, Void> buyButton = new TableColumn("Buy a Book");

            Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<>() {
                @Override
                public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                    final TableCell<Book, Void> cell = new TableCell<Book, Void>() {

                        private final Button buyButton = new Button("Buy");

                        {
                            buyButton.setOnAction((ActionEvent event) -> {
                                //On button click
                                Stage stage = new Stage();

                                Parent root = null;
                                try {
                                    root = FXMLLoader.load(getClass().getClassLoader().getResource("buy.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage.setTitle("Buy a Book");
                                stage.setScene(new Scene(root, 435, 351));
                                stage.show();
                            });
                        }

                        @Override
                        public void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(buyButton);
                            }
                        }
                    };
                    return cell;
                }
            };
            buyButton.setCellFactory(cellFactory);

            bookTable.getColumns().add(buyButton);
        }
}
