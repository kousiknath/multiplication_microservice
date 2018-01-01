package microservices.book.socialmultiplication.multiplication.service;

import microservices.book.socialmultiplication.multiplication.domain.User;

public interface UserService {
    User getUser(Long userId);
}
