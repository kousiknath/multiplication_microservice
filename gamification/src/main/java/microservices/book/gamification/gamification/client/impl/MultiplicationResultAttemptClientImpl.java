package microservices.book.gamification.gamification.client.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import microservices.book.gamification.gamification.client.MultiplicationResultAttemptClient;
import microservices.book.gamification.gamification.client.dto.MultiplicationResultAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class MultiplicationResultAttemptClientImpl implements MultiplicationResultAttemptClient{
    private RestTemplate restTemplate;
    private String multiplicationHost;

    @Autowired
    public MultiplicationResultAttemptClientImpl(final RestTemplate restTemplate,
                                                 @Value("${multiplicationHost}") final String multiplicationHost){
        this.restTemplate = restTemplate;
        this.multiplicationHost = multiplicationHost;
    }


    @Override
    @HystrixCommand(defaultFallback = "defaultResult")
    public MultiplicationResultAttempt retrieveMultiplicationResultAttempt(final Long attemptId) {
        return restTemplate.getForObject(multiplicationHost + "/results/" + attemptId, MultiplicationResultAttempt.class);
    }

    private MultiplicationResultAttempt defaultResult(){
        log.info("Providing default MultiplicationResultAttempt");
        return new MultiplicationResultAttempt("systemUser", 10, 10, 100, true);
    }
}
