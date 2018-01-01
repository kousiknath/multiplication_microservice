package microservices.book.socialmultiplication.multiplication.controller;

import lombok.extern.slf4j.Slf4j;
import microservices.book.socialmultiplication.multiplication.domain.Multiplication;
import microservices.book.socialmultiplication.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/multiplications")
public class MultiplicationController {
    private final MultiplicationService multiplicationService;
    private final int portNumber;

    @Autowired
    public MultiplicationController(final MultiplicationService multiplicationService,
                                    @Value("${server.port}") final int portNumber){
        this.multiplicationService = multiplicationService;
        this.portNumber = portNumber;
    }

    @GetMapping("/random")
    Multiplication getRandomMultiplication(){
        log.info("Generating random multiplication @ port: {} ", this.portNumber);
        return multiplicationService.createRandomMultiplication();
    }
}
