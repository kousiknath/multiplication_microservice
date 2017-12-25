package microservices.book.gamification.gamification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.book.gamification.gamification.domain.Badge;
import microservices.book.gamification.gamification.domain.BadgeCard;
import microservices.book.gamification.gamification.domain.GameStats;
import microservices.book.gamification.gamification.domain.ScoreCard;
import microservices.book.gamification.gamification.repository.BadgeCardRepository;
import microservices.book.gamification.gamification.repository.ScoreCardRepository;
import microservices.book.gamification.gamification.service.GameService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(UserStatsController.class)
public class UserStatsControllerTest {
    @MockBean
    private GameService gameService;
    @Mock
    private BadgeCardRepository badgeCardRepository;
    @Mock
    private ScoreCardRepository scoreCardRepository;

    @Autowired
    MockMvc mvc;

    private JacksonTester<GameStats> jsonResult;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getUserStatsTest() throws Exception {
        Long userId = 1L;
        List<BadgeCard> badgeCards = new ArrayList<>();

        badgeCards.add(new BadgeCard(userId, Badge.FIRST_WON));
        badgeCards.add(new BadgeCard(userId, Badge.BRONZE_MULTIPLICATOR));
        badgeCards.add(new BadgeCard(userId, Badge.SILVER_MULTIPLICATOR));

//        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId)).willReturn(badgeCards);
//        given(scoreCardRepository.getTotalScoreForUser(userId)).willReturn(120);

        GameStats expectedStats = new GameStats(userId, scoreCardRepository.getTotalScoreForUser(userId),
                badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList()));

        given(gameService.retrieveStatsForUser(userId)).willReturn(expectedStats);

        MockHttpServletResponse response = mvc.perform(get("/stats")
                    .param("userId", String.valueOf(userId)).accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResult.write(expectedStats).getJson());
    }
}
