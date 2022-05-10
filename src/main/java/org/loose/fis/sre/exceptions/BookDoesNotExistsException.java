package org.loose.fis.sre.exceptions;

public class BookDoesNotExistsException extends Exception{
    public BookDoesNotExistsException(String bookName) {
        super(String.format("Book with the name " + bookName +  " does not exist!"));
    }
}
