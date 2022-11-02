package com.example.Eindopdracht.exceptions;

public class CannotUpdateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CannotUpdateException() {
        super();
    }

    public CannotUpdateException(String message) {
        super(message);
    }

}

