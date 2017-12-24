package microservices.book.socialmultiplication.multiplication.service.impl;

import microservices.book.socialmultiplication.multiplication.service.RandomGeneratorService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kousik on 16/12/17.
 */
public class RandomGeneratorServiceImplTest {
    private RandomGeneratorServiceImpl randomGeneratorService;

    @Before
    public void setUp(){
        randomGeneratorService = new RandomGeneratorServiceImpl();
    }

    @Test
    public void checkRandomNumberIsBetweenExpectedLimit() throws Exception{
        List<Integer> randomFactors = IntStream.range(0, 1000).map(i -> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());
        assertThat(randomFactors).containsOnlyElementsOf(IntStream.range(11, 100)
                .boxed()
                .collect(Collectors.toList()));
    }

}
