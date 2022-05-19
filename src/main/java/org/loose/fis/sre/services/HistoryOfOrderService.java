package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Book;
import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class HistoryOfOrderService {
    private static ObjectRepository<Book> orderHistoryRepository;
    private static Nitrite database;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("order_books_database.db").toFile())
                .openOrCreate("test4", "test4");

        orderHistoryRepository = database.getRepository(Book.class);
    }

    public static void addBuyedBook(String bookName, String authorName, String bookType, String publishingHouse, float bookPrice){
        orderHistoryRepository.insert(new Book(bookName, authorName, bookType, publishingHouse, bookPrice));
    }

    public static ObjectRepository<Book> getBookRepository() {
        return orderHistoryRepository;
    }

    public static Nitrite getDatabase() { return database; }
}
