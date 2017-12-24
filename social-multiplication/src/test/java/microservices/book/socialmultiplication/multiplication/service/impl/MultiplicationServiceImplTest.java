package microservices.book.socialmultiplication.multiplication.service.impl;

import microservices.book.socialmultiplication.multiplication.domain.Multiplication;
import microservices.book.socialmultiplication.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplication.multiplication.domain.User;
import microservices.book.socialmultiplication.multiplication.event.EventDispatcher;
import microservices.book.socialmultiplication.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.socialmultiplication.multiplication.repository.UserRepository;
import microservices.book.socialmultiplication.multiplication.service.RandomGeneratorService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.times;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kousik on 17/12/17.
 */
public class MultiplicationServiceImplTest {
    private MultiplicationServiceImpl multiplicationService;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EventDispatcher eventDispatcher;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        multiplicationService = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository, userRepository, eventDispatcher);
    }

    @Test
    public void createRandomMultiplicationTest(){
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        Multiplication multiplication = multiplicationService.createRandomMultiplication();
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
    }

    @Test
    public void checkCorrectAttemptTest(){
        Multiplication multiplication = new Multiplication(50, 30);
        User user = new User("Test");
        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(user, multiplication, 1500, false);
        MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 1500, true);
        given(userRepository.findByAlias("Test")).willReturn(Optional.empty());

        boolean attemptStatus = multiplicationService.checkAttempt(multiplicationResultAttempt);

        assertThat(attemptStatus).isTrue();
        verify(attemptRepository, times(1)).save(verifiedAttempt); //times(1) is default.
    }

    @Test
    public void checkWrongAttemptTest(){
        Multiplication multiplication = new Multiplication(50, 30);
        User user = new User("Test");
        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(user, multiplication, 3010, false);

        given(userRepository.findByAlias("Test")).willReturn(Optional.empty());

        boolean attemptStatus = multiplicationService.checkAttempt(multiplicationResultAttempt);

        assertThat(attemptStatus).isFalse();
        verify(attemptRepository).save(multiplicationResultAttempt);
    }

    @Test
    public void checkAndGetCorrectAttempt(){
        Multiplication multiplication = new Multiplication(50, 30);
        User user = new User("Test");
        MultiplicationResultAttempt mockAttempt = Mockito.mock(MultiplicationResultAttempt.class);
    }

    @Test
    public void retrieveStatsTest(){
        Multiplication multiplication1 = new Multiplication(50, 30);
        Multiplication multiplication2 = new Multiplication(20, 30);
        User user = new User("Test");
        MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication1, 1500, false);
        MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication2, 600, false);

        List<MultiplicationResultAttempt> attempts = Lists.newArrayList(attempt1, attempt2);
        //given(userRepository.findByAlias("Test")).willReturn(Optional.empty());
        given(attemptRepository.findTop5ByUserAliasOrderByIdDesc("Test")).willReturn(attempts);

        List<MultiplicationResultAttempt> latestAttemptResult = multiplicationService.getStatsForUser("Test");
        assertThat(latestAttemptResult).isEqualTo(attempts);
    }
}
