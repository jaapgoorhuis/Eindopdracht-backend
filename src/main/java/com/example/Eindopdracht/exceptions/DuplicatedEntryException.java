package com.example.Eindopdracht.exceptions;
public class DuplicatedEntryException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicatedEntryException() {
        super();
    }

    public DuplicatedEntryException(String message) {
        super(message);
    }

}
