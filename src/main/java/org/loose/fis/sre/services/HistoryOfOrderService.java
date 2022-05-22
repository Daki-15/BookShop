package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.BookNameAlreadyExistsException;
import org.loose.fis.sre.model.SoldBook;

import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class HistoryOfOrderService {
    private static ObjectRepository<SoldBook> orderHistoryRepository;
    private static Nitrite database;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("order_books_database.db").toFile())
                .openOrCreate("test4", "test4");

        orderHistoryRepository = database.getRepository(SoldBook.class);
    }

    public static void addBoughtBook(String bookName, String authorName, String bookType, String publishingHouse, float bookPrice) throws BookNameAlreadyExistsException {
        checkBookNameAlreadyExists(bookName);
        orderHistoryRepository.insert(new SoldBook(bookName, authorName, bookType, publishingHouse, bookPrice));
    }

    public static void checkBookNameAlreadyExists(String bookName) throws BookNameAlreadyExistsException {
        for (SoldBook book : orderHistoryRepository.find()) {
            if (Objects.equals(bookName, book.getBookName()))
                throw new BookNameAlreadyExistsException(bookName);
        }
    }

    public static ObjectRepository<SoldBook> getBookRepository() {
        return orderHistoryRepository;
    }

    public static Nitrite getDatabase() {
        return database;
    }
}
