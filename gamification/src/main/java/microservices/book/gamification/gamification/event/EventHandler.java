package microservices.book.gamification.gamification.event;

import lombok.extern.slf4j.Slf4j;
import microservices.book.gamification.gamification.service.GameService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventHandler {
    private GameService gameService;

    public EventHandler(GameService gameService){
        this.gameService = gameService;
    }

    @RabbitListener(queues = "${multiplication.queue}")
    void handleMultiplicationSolvedEvent(final MultiplicationSolvedEvent event){
        log.info("Multiplication solved event received: {}", event.getMultiplicationResultAttemptId());

        try{
            gameService.newAttemptForUser(event.getUserId(), event.getMultiplicationResultAttemptId(), event.isCorrect());

        } catch (final Exception ex){
            log.error("Error processing multiplicartion solved event: ", ex);
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
