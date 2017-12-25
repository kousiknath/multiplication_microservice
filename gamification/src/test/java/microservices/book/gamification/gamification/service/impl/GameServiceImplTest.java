package microservices.book.gamification.gamification.service.impl;

import microservices.book.gamification.gamification.domain.Badge;
import microservices.book.gamification.gamification.domain.GameStats;
import microservices.book.gamification.gamification.domain.ScoreCard;
import microservices.book.gamification.gamification.repository.BadgeCardRepository;
import microservices.book.gamification.gamification.repository.ScoreCardRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GameServiceImplTest {
    private GameServiceImpl gameService;

    @Mock
    private ScoreCardRepository scoreCardRepository;
    @Mock
    private BadgeCardRepository badgeCardRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        gameService = new GameServiceImpl(scoreCardRepository, badgeCardRepository);
    }

    @Test
    public void firstOwnAttemptForUserTest(){
        Long userId = 1L;
        Long attemptId = 1L;
        ScoreCard scoreCard = new ScoreCard(userId, attemptId);

        // Mock the components like repository which are internally used by the required services.
        given(scoreCardRepository.save(scoreCard)).willReturn(scoreCard);
        given(scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId)).willReturn(Lists.newArrayList(scoreCard));
        given(scoreCardRepository.getTotalScoreForUser(userId)).willReturn(scoreCard.getScore());
        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId)).willReturn(Lists.newArrayList());

        List<Badge> expectedBadges = Lists.newArrayList(Badge.FIRST_WON);
        GameStats expectedGameStats = new GameStats(userId, 10, expectedBadges);

        GameStats actualGameStats = gameService.newAttemptForUser(userId, attemptId, true);
        assertThat(actualGameStats).isEqualTo(expectedGameStats);
    }

    @Test
    public void bronzeMultiplicatorBadgeForUserTest(){
        Long userId = 1L;
        List<ScoreCard> scoreCards = new ArrayList<>();

        for(int i = 0; i < 15; i++){
            ScoreCard card = new ScoreCard(userId, (long)i + 1);
            given(scoreCardRepository.save(card)).willReturn(card);
            scoreCards.add(card);
        }

        given(scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId)).willReturn(scoreCards);
        given(scoreCardRepository.getTotalScoreForUser(userId)).willReturn(150);
        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId)).willReturn(Lists.newArrayList());

        List<Badge> expectedBadges = Lists.newArrayList(Badge.BRONZE_MULTIPLICATOR);
        GameStats expectedStats = new GameStats(userId, scoreCards.get(scoreCards.size() - 1).getScore(), expectedBadges);

        GameStats actualStats = gameService.newAttemptForUser(userId, scoreCards.get(scoreCards.size() - 1).getAttemptId(), true);
        assertThat(actualStats).isEqualTo(expectedStats);
    }
}
