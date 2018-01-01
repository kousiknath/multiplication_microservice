package microservices.book.socialmultiplication.multiplication.service.impl;

import microservices.book.socialmultiplication.multiplication.domain.User;
import microservices.book.socialmultiplication.multiplication.repository.UserRepository;
import microservices.book.socialmultiplication.multiplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
