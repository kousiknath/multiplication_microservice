package microservices.book.gamification.gamification.service.impl;

import lombok.extern.slf4j.Slf4j;
import microservices.book.gamification.gamification.client.MultiplicationResultAttemptClient;
import microservices.book.gamification.gamification.client.dto.MultiplicationResultAttempt;
import microservices.book.gamification.gamification.domain.Badge;
import microservices.book.gamification.gamification.domain.BadgeCard;
import microservices.book.gamification.gamification.domain.GameStats;
import microservices.book.gamification.gamification.domain.ScoreCard;
import microservices.book.gamification.gamification.repository.BadgeCardRepository;
import microservices.book.gamification.gamification.repository.ScoreCardRepository;
import microservices.book.gamification.gamification.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameServiceImpl implements GameService{
    public static final int LUCKY_NUMBER = 42;
    private ScoreCardRepository scoreCardRepository;
    private BadgeCardRepository badgeCardRepository;
    private MultiplicationResultAttemptClient multiplicationResultAttemptClient;

    @Autowired
    public GameServiceImpl(final ScoreCardRepository scoreCardRepository,
                           final BadgeCardRepository badgeCardRepository,
                           final MultiplicationResultAttemptClient multiplicationResultAttemptClient){
        this.scoreCardRepository = scoreCardRepository;
        this.badgeCardRepository = badgeCardRepository;
        this.multiplicationResultAttemptClient = multiplicationResultAttemptClient;
    }

    @Override
    public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {
        ScoreCard scoreCard = new ScoreCard(userId, attemptId);
        scoreCardRepository.save(scoreCard);

        List<BadgeCard> badgeCards = processForBadges(userId, attemptId, correct);

        return new GameStats(userId, scoreCard.getScore(),
                badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList()));
    }

    List<BadgeCard> processForBadges(Long userId, Long attemptId, boolean correct){
        int totalScore = scoreCardRepository.getTotalScoreForUser(userId);

        List<ScoreCard> scoreCards = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
        List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);

        checkAndGiveBadgeBasedOnScore(badgeCards, Badge.BRONZE_MULTIPLICATOR, totalScore, 100, userId).ifPresent(badgeCards::add);
        checkAndGiveBadgeBasedOnScore(badgeCards, Badge.SILVER_MULTIPLICATOR, totalScore, 300, userId).ifPresent(badgeCards::add);
        checkAndGiveBadgeBasedOnScore(badgeCards, Badge.GOLD_MULTIPLICATOR, totalScore, 500, userId).ifPresent(badgeCards::add);

        if(scoreCards.size() == 1 && correct && !containsBadge(badgeCards, Badge.FIRST_WON)){
            BadgeCard firstWonBadge = giveBadgeToUser(userId, Badge.FIRST_WON);
            badgeCards.add(firstWonBadge);
        }

        MultiplicationResultAttempt attempt = multiplicationResultAttemptClient.retrieveMultiplicationResultAttempt(attemptId);
        if(!containsBadge(badgeCards, Badge.LUCKY_NUMBER) &&
                (attempt.getMultiplicationFactorA() == LUCKY_NUMBER || attempt.getMultiplicationFactorB() == LUCKY_NUMBER)){
            BadgeCard luckyNumberBadgeCard = giveBadgeToUser(userId, Badge.LUCKY_NUMBER);
            badgeCards.add(luckyNumberBadgeCard);
        }

        return badgeCards;
    }

    private Optional<BadgeCard> checkAndGiveBadgeBasedOnScore(final List<BadgeCard> badgeCards,
                                                              final Badge badge, final int totalScore,
                                                              final int thresholdScore, final Long userId){
        if(totalScore >= thresholdScore && !containsBadge(badgeCards, badge)){
            return Optional.of(giveBadgeToUser(userId, badge));
        }

        return Optional.empty();
    }

    private boolean containsBadge(final List<BadgeCard> badgeCards, final Badge badge){
        return badgeCards.stream().anyMatch(b -> b.getBadge().equals(badge));
    }

    private BadgeCard giveBadgeToUser(Long userId, Badge badge){
        BadgeCard badgeCard = new BadgeCard(userId, badge);
        badgeCardRepository.save(badgeCard);

        return badgeCard;
    }

    @Override
    public GameStats retrieveStatsForUser(Long userId) {
        int totalScore = scoreCardRepository.getTotalScoreForUser(userId);
        List<BadgeCard> badgeCards  = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
        return new GameStats(userId, totalScore,
                badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList()));
    }

    @Override
    public ScoreCard getScoreForAttempt(Long attemptId) {
        List<ScoreCard> scoreCards = scoreCardRepository.findByAttemptId(attemptId);

        if(scoreCards.size() == 0)
            return null;

        return scoreCards.get(0);
    }
}
