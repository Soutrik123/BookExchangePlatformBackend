package org.fsd.assignment.backend.services;

import lombok.RequiredArgsConstructor;
import org.fsd.assignment.backend.model.entity.Book;
import org.fsd.assignment.backend.repository.BooksRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BooksRepository booksRepository;

    public Mono<Book> addBook(Book book) {
        return booksRepository.save(book);
    }

    public Flux<Book> getAllBooks() {
        return booksRepository.findAll();
    }
    public Mono<Book> getBookByName(String name) {
        return booksRepository.findByTitle(name);

    }

}
