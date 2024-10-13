package com.polarbookshop.catalogservice.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("test-data")
@RequiredArgsConstructor
public class BookDataLoader {

    private final BookRepository bookRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void load() {
        bookRepository.deleteAll();
        Book book1 = Book.of("1234567891", "Northern Lights",
                "Lyra Silverstar", 9.90, "");
        Book book2 = Book.of("1234567892", "Polar Journey",
                "Iorek Polarson", 12.90, "");

        bookRepository.saveAll(List.of(book1, book2));

//        bookRepository.save(book1);
//        bookRepository.save(book2);
    }
}
