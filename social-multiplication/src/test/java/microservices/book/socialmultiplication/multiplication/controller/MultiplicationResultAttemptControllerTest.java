package microservices.book.socialmultiplication.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.book.socialmultiplication.multiplication.domain.Multiplication;
import microservices.book.socialmultiplication.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplication.multiplication.domain.User;
import microservices.book.socialmultiplication.multiplication.service.MultiplicationService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MultiplicationResultAttemptController.class)
public class MultiplicationResultAttemptControllerTest {
    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationResultAttempt> jsonBody;
    private JacksonTester<MultiplicationResultAttempt> jsonResponse;
    private JacksonTester<List<MultiplicationResultAttempt>> attemptsResultList;

    @Before
    public void setUp(){
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postResultReturnCorrect() throws Exception{
        this.genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnNotCorrect() throws Exception{
        this.genericParameterizedTest(false);
    }

    private void genericParameterizedTest(final boolean correct) throws Exception{
        // We are not testing the service. We are testing the controller only. So we will mock all other things.
        given(multiplicationService.checkAttempt(any(MultiplicationResultAttempt.class))).willReturn(correct);

        User user = new User("Test");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500, correct);

        MockHttpServletResponse response = mvc.perform(post("/results")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody.write(attempt).getJson()))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonResponse
                        .write(new MultiplicationResultAttempt(user, multiplication, 3500, correct))
                        .getJson());
    }
    @Test
    public void retrieveUserStatsTest() throws Exception {
        Multiplication multiplication1 = new Multiplication(50, 30);
        Multiplication multiplication2 = new Multiplication(20, 30);
        User user = new User("Test");
        MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication1, 1500, false);
        MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication2, 600, false);

        List<MultiplicationResultAttempt> attemptList = Lists.newArrayList(attempt1, attempt2);

        given(multiplicationService.getStatsForUser("Test")).willReturn(attemptList);

        MockHttpServletResponse response = mvc.perform(get("/results/stats")
                .param("alias", "Test")
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(attemptsResultList.write(attemptList).getJson());
    }

    @Test
    public void getMultiplicationAttemptByIdTest() throws Exception{
        Multiplication multiplication = new Multiplication(30, 10);
        User user = new User("Test");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(
                user, multiplication, 300, false);

        given(multiplicationService.getAttemptById(1L)).willReturn(attempt);

        MockHttpServletResponse response = mvc.perform(get("/results/1")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(attempt).getJson());
    }
}
