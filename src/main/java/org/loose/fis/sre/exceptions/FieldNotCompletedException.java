package org.loose.fis.sre.exceptions;

public class FieldNotCompletedException extends Exception {
    public FieldNotCompletedException() {
        super("Please complete all fields!");
    }
}
