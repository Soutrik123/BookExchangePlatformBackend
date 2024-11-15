package org.fsd.assignment.backend.router;

import org.fsd.assignment.backend.handler.BookExchangeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BookExchangeRouter {

    public static final String BASE_URL = "/v1/api";
    public static final String USER_REGISTER = "/user/register";
    public static final String BOOKS_SEARCH= "/books/search";
    public static final String BOOKS_ADD = "/books/add";
    public static final String SEARCH_ID = "searchId";


   @Bean
    RouterFunction<ServerResponse> routes(BookExchangeHandler handler) {
        return RouterFunctions.route()
                .path(BASE_URL, () -> bookExchangeRoutes(handler) )
                .build();

    }

    private RouterFunction<ServerResponse> bookExchangeRoutes(BookExchangeHandler handler) {

        return RouterFunctions.route()
                .GET(RequestPredicates.path(BOOKS_SEARCH +"/{"+ SEARCH_ID +"}"),handler::getBooksBySearchId)
                .POST(RequestPredicates.path(BOOKS_ADD),handler::handleBookAdd)
                .POST(RequestPredicates.path(USER_REGISTER),handler::handleUserRegistration)
                .build();
    }

}
