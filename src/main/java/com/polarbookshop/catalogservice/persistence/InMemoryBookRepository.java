//package com.polarbookshop.catalogservice.persistence;
//
//import com.polarbookshop.catalogservice.domain.Book;
//import com.polarbookshop.catalogservice.domain.BookRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Map;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Repository
//public class InMemoryBookRepository implements BookRepository {
//
//    private final Map<String, Book> books = new ConcurrentHashMap<>();
//
//    @Override
//    public Iterable<Book> findAll() {
//        return books.values();
//    }
//
//    @Override
//    public Optional<Book> findByIsbn(String isbn) {
//        return existsByIsbn(isbn) ?
//                Optional.of(books.get(isbn)) : Optional.empty();
//    }
//
//    @Override
//    public boolean existsByIsbn(String isbn) {
//        return books.containsKey(isbn);
//    }
//
//    @Override
//    public Book save(Book book) {
//        String isbn = Objects.requireNonNull(Objects.requireNonNull(book).isbn());
//        return books.put(isbn, book);
//    }
//
//    @Override
//    public void deleteByIsbn(String isbn) {
//        books.remove(isbn);
//    }
//}
//不再使用
