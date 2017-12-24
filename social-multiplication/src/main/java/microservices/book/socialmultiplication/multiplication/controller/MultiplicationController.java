package microservices.book.socialmultiplication.multiplication.controller;

import microservices.book.socialmultiplication.multiplication.domain.Multiplication;
import microservices.book.socialmultiplication.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/multiplications")
public class MultiplicationController {
    @Autowired
    private MultiplicationService multiplicationService;

    @GetMapping("/random")
    Multiplication getRandomMultiplication(){
        return multiplicationService.createRandomMultiplication();
    }
}
