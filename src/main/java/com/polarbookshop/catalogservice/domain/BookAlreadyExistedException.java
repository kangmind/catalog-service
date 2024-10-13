package com.polarbookshop.catalogservice.domain;

public class BookAlreadyExistedException extends RuntimeException {

    public BookAlreadyExistedException(String isbn) {
        super("Book with ISBN " + isbn + " already existed");
    }
}
