package microservices.book.socialmultiplication.multiplication.service;

import microservices.book.socialmultiplication.multiplication.domain.Multiplication;
import microservices.book.socialmultiplication.multiplication.domain.MultiplicationResultAttempt;

import java.util.List;

/**
 * Created by kousik on 16/12/17.
 */
public interface MultiplicationService {
    Multiplication createRandomMultiplication();
    boolean checkAttempt(final MultiplicationResultAttempt multiplicationResultAttempt);
    List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
    MultiplicationResultAttempt getAttemptById(Long id);
}
