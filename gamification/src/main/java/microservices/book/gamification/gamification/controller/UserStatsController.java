package microservices.book.gamification.gamification.controller;

import microservices.book.gamification.gamification.domain.GameStats;
import microservices.book.gamification.gamification.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class UserStatsController {
    private final GameService gameService;

    @Autowired
    UserStatsController(final GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<GameStats> getUserStats(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(gameService.retrieveStatsForUser(userId));
    }
}
