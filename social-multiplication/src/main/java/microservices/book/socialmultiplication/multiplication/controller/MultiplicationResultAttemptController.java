package microservices.book.socialmultiplication.multiplication.controller;

import microservices.book.socialmultiplication.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplication.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public final class MultiplicationResultAttemptController {
    private final MultiplicationService multiplicationService;

    @Autowired
    MultiplicationResultAttemptController(MultiplicationService service){
        this.multiplicationService = service;
    }

    @PostMapping
    ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt attempt){
        boolean correct = multiplicationService.checkAttempt(attempt);
        MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt(attempt.getUser(),
                attempt.getMultiplication(), attempt.getResultAttempt(), correct);

        return ResponseEntity.ok(resultAttempt);
    }

    @GetMapping(value = "/stats")
    ResponseEntity<List<MultiplicationResultAttempt>> getStats(@RequestParam String alias){
        List<MultiplicationResultAttempt> userAttempts = multiplicationService.getStatsForUser(alias);
        return ResponseEntity.ok(userAttempts);
    }

}
