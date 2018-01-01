package microservices.book.socialmultiplication.multiplication.controller;

import lombok.extern.slf4j.Slf4j;
import microservices.book.socialmultiplication.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplication.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/results")
public final class MultiplicationResultAttemptController {
    private final MultiplicationService multiplicationService;
    private final int serverPort;

    @Autowired
    MultiplicationResultAttemptController(MultiplicationService service,
                                          @Value("${server.port}") final int port){
        this.multiplicationService = service;
        this.serverPort = port;
    }

    @PostMapping
    ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt attempt){
        log.info("Received result attempt {} from server @ {}", attempt.getId(), this.serverPort);

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

    @GetMapping("/{attemptId}")
    ResponseEntity<MultiplicationResultAttempt> getMultiplicationAttemptById(
            @PathVariable("attemptId") Long attemptId){
        return ResponseEntity.ok(multiplicationService.getAttemptById(attemptId));
    }

}
