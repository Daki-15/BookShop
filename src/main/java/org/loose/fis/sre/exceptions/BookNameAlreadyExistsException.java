package org.loose.fis.sre.exceptions;

public class BookNameAlreadyExistsException extends Exception {
    public BookNameAlreadyExistsException(String bookName) {
        super(String.format("A book with the name %s already exists!", bookName));
    }
}
