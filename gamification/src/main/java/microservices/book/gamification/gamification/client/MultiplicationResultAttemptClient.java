package microservices.book.gamification.gamification.client;

import microservices.book.gamification.gamification.client.dto.MultiplicationResultAttempt;

public interface MultiplicationResultAttemptClient {
    MultiplicationResultAttempt retrieveMultiplicationResultAttempt(final Long attemptId);
}
