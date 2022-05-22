package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.BookNameAlreadyExistsException;
import org.loose.fis.sre.exceptions.FieldNotCompletedException;
import org.loose.fis.sre.model.Book;

import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class BookService {
    private static ObjectRepository<Book> bookRepository;
    private static Nitrite database;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("books_database.db").toFile())
                .openOrCreate("test2", "test2");

        bookRepository = database.getRepository(Book.class);
    }

    public static void addBook(String bookName, String authorName, String bookType, String publishingHouse, float bookPrice) throws BookNameAlreadyExistsException, FieldNotCompletedException {
        checkBookNameAlreadyExists(bookName);
        checkAllFieldsAreCompleted(bookName, authorName, bookType, publishingHouse, bookPrice);
        bookRepository.insert(new Book(bookName, authorName, bookType, publishingHouse, bookPrice));
    }

    public static void checkBookNameAlreadyExists(String bookName) throws BookNameAlreadyExistsException {
        for (Book book : bookRepository.find()) {
            if (Objects.equals(bookName, book.getBookName()))
                throw new BookNameAlreadyExistsException(bookName);
        }
    }

    private static void checkAllFieldsAreCompleted(String bookName, String authorName, String bookType, String publishingHouse, float bookPrice) throws FieldNotCompletedException {
        if (bookName.trim().isEmpty() || authorName.trim().isEmpty() || bookType.trim().isEmpty() || publishingHouse.trim().isEmpty() || String.valueOf(bookPrice).trim().isEmpty())
            throw new FieldNotCompletedException();
    }

    public static boolean checkIfBookPriceIsFloat(String price) {
        try {
            Float.parseFloat(price);
            return true;

        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static void checkBookNameFieldIsCompleted(String bookName) throws FieldNotCompletedException {
        if (bookName.trim().isEmpty())
            throw new FieldNotCompletedException();
    }

    public static ObjectRepository<Book> getBookRepository() {
        return bookRepository;
    }

    public static Nitrite getDatabase() {
        return database;
    }
}
