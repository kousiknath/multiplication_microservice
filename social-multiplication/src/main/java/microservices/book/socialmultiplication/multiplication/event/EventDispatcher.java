package microservices.book.socialmultiplication.multiplication.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {
    private final RabbitTemplate rabbitTemplate;
    private final String multiplicationExchange;
    private final String multiplicationRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${multiplication.exchange}") String exchangeName,
                    @Value("${multiplication.solved.key}") String routingKey){
        this.rabbitTemplate = rabbitTemplate;
        this.multiplicationExchange = exchangeName;
        this.multiplicationRoutingKey = routingKey;
    }

    public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent){
        rabbitTemplate.convertAndSend(this.multiplicationExchange,
                this.multiplicationRoutingKey,
                multiplicationSolvedEvent);
    }
}
