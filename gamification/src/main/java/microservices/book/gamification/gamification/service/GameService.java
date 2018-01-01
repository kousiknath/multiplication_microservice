package microservices.book.gamification.gamification.service;

import microservices.book.gamification.gamification.domain.GameStats;
import microservices.book.gamification.gamification.domain.ScoreCard;

public interface GameService {
    GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct);
    GameStats retrieveStatsForUser(Long userId);
    ScoreCard getScoreForAttempt(Long attemptId);
}
