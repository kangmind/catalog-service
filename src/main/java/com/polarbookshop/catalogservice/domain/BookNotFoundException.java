package com.polarbookshop.catalogservice.domain;

import lombok.Getter;

public class BookNotFoundException extends RuntimeException {

    @Getter
    private final String isbn;

    public BookNotFoundException(String isbn) {
        super("Book with ISBN " + isbn + " not found");
        this.isbn = isbn;
    }
}
