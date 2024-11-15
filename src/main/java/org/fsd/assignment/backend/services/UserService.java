package org.fsd.assignment.backend.services;

import lombok.RequiredArgsConstructor;
import org.fsd.assignment.backend.model.entity.User;
import org.fsd.assignment.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Mono<User> addUser (User user) {
        return userRepository.save(user);
    }
}
