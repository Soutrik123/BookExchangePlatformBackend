package org.fsd.assignment.backend.handler;

import lombok.RequiredArgsConstructor;
import org.fsd.assignment.backend.model.entity.Book;
import org.fsd.assignment.backend.model.entity.User;
import org.fsd.assignment.backend.services.BookService;
import org.fsd.assignment.backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookExchangeHandler {
    private final BookService bookService;
    private final UserService userService;

    public Mono<ServerResponse> getBooksBySearchId(ServerRequest request) {
        String searchId = request.pathVariable("searchId");

        return bookService.getBookByName(searchId)
                .flatMap(book -> ServerResponse.ok().bodyValue(book))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .bodyValue("An unexpected error occurred: " + e.getMessage()));
    }


    public Mono<ServerResponse> handleBookAdd(ServerRequest request) {
        return request.bodyToMono(Book.class)
                .flatMap(book -> {
                    if (book.getTitle() == null || book.getAuthor() == null) {
                        return Mono.error(new IllegalArgumentException("Book title and author are required"));
                    }
                    // Add the book using the service
                    return bookService.addBook(book)
                            .flatMap(addedBook -> ServerResponse.ok().bodyValue(addedBook)) // Return the added book
                            .switchIfEmpty(ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .bodyValue("Book could not be added"));
                })
                .onErrorResume(e -> {
                    if (e instanceof IllegalArgumentException) {
                        return ServerResponse.badRequest().bodyValue(e.getMessage());
                    }
                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .bodyValue("An unexpected error occurred");
                });
    }


    public Mono<ServerResponse> handleUserRegistration(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(user -> {
                    if (user.getName() == null || user.getEmail() == null) {
                        return Mono.error(new IllegalArgumentException("User name and email are required"));
                    }
                    // Add the user using the service
                    return userService.addUser(user)
                            .flatMap(addedUser -> ServerResponse.ok().bodyValue(addedUser)) // Return the registered user
                            .switchIfEmpty(ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .bodyValue("User could not be registered"));
                })
                .onErrorResume(e -> {
                    // Handle errors gracefully
                    if (e instanceof IllegalArgumentException) {
                        return ServerResponse.badRequest().bodyValue(e.getMessage());
                    }
                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .bodyValue("An unexpected error occurred");
                });
    }






}
