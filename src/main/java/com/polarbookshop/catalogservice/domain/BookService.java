package com.polarbookshop.catalogservice.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }


    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistedException(book.isbn());
        }
        return bookRepository.save(book);
    }

    @Transactional
    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book updateBook(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(
                        existingBook -> bookRepository.save(
                                new Book(
                                        existingBook.id(),
                                        existingBook.version(),
                                        existingBook.createdDate(),
                                        existingBook.lastModifiedDate(),
                                        existingBook.isbn(),
                                        book.title(),
                                        book.author(),
                                        book.price(),
                                        book.publisher()
                                )
                        )
                )
                .orElse(addBookToCatalog(book));
    }
}
