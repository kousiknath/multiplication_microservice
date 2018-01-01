package microservices.book.gamification.gamification.controller;

import microservices.book.gamification.gamification.domain.ScoreCard;
import microservices.book.gamification.gamification.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scores")
public class ScoreController {
    private final GameService gameService;

    @Autowired
    public ScoreController(final GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/{attemptId}")
    public ScoreCard getScoreForAttempt(@PathVariable("attemptId") final Long attemptId){
        return gameService.getScoreForAttempt(attemptId);
    }
}
