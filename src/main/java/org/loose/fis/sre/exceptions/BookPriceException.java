package org.loose.fis.sre.exceptions;

public class BookPriceException extends Exception {
    public BookPriceException() {
        super("Incorrect price!");
    }
}
