package org.fsd.assignment.backend.repository;

import org.fsd.assignment.backend.model.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<User, String> {
    Mono<User> findByName(String username);
    Mono<User> findByEmail(String email);
}
