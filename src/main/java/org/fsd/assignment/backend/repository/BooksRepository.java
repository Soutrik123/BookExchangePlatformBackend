package org.fsd.assignment.backend.repository;

import org.fsd.assignment.backend.model.entity.Book;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BooksRepository extends R2dbcRepository<Book, String> {

    Mono<Book> findByAuthor(String authorName);

    Mono<Book> findByTitle(String title);


}
