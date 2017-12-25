package microservices.book.gamification.gamification.service.impl;

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
public class GameServiceImpl implements GameService{
    private ScoreCardRepository scoreCardRepository;
    private BadgeCardRepository badgeCardRepository;

    @Autowired
    public GameServiceImpl(ScoreCardRepository scoreCardRepository, BadgeCardRepository badgeCardRepository){
        this.scoreCardRepository = scoreCardRepository;
        this.badgeCardRepository = badgeCardRepository;
    }

    @Override
    public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {
        ScoreCard scoreCard = new ScoreCard(userId, attemptId);
        scoreCardRepository.save(scoreCard);

        List<BadgeCard> badgeCards = processForBadges(userId, correct);

        return new GameStats(userId, scoreCard.getScore(),
                badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList()));
    }

    List<BadgeCard> processForBadges(Long userId, boolean correct){
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
}
