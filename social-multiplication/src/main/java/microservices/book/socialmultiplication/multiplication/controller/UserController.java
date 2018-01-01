package microservices.book.socialmultiplication.multiplication.controller;

import microservices.book.socialmultiplication.multiplication.domain.User;
import microservices.book.socialmultiplication.multiplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getUser(Long userId){
        return userService.getUser(userId);
    }
}
