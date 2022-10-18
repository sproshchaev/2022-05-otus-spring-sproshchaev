package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.Main;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("AnswerServiceImplTest is testing ")
class AnswerServiceImplTest {
    private final static int TEST_NUMBER = 1;
    private final static int CORRECT_ANSWER_NUMBER = 2;
    private final static int WRONG_ANSWER_NUMBER = 3;
    private final static int CORRECT_INPUT = 1;
    private final static int INCORRECT_INPUT = 4;
    private final static int NUMBER_CORRECT_ANSWERS_FOR_TEST_NOT_PASSED = 2;
    private final static int NUMBER_CORRECT_ANSWERS_FOR_TEST_PASSED = 3;
    private AnswerService answerService;

    public AnswerServiceImplTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        this.answerService = context.getBean(AnswerService.class);
    }

    @Test
    @DisplayName("method shouldCheckCorrectAnswerTrue should check the correctness of the answerService by the test number")
    void shouldCheckCorrectAnswerTrue() {
        assertThat(answerService.checkCorrectAnswer(TEST_NUMBER, CORRECT_ANSWER_NUMBER)).isEqualTo(true);
    }

    @Test
    @DisplayName("method shouldCheckCorrectAnswerFalse should check the correctness of the answerService by the test number")
    void shouldCheckCorrectAnswerFalse() {
        assertThat(answerService.checkCorrectAnswer(TEST_NUMBER, WRONG_ANSWER_NUMBER)).isEqualTo(false);
    }

    @Test
    @DisplayName("method shouldIsCorrectInputTrue checks the correctness of the entered data from the console")
    void shouldIsCorrectInputTrue() {
        assertThat(answerService.isCorrectInput(CORRECT_INPUT, TEST_NUMBER)).isEqualTo(true);
    }

    @Test
    @DisplayName("method shouldIsCorrectInputFalse checks the correctness of the entered data from the console")
    void shouldIsCorrectInputFalse() {
        assertThat(answerService.isCorrectInput(INCORRECT_INPUT, TEST_NUMBER)).isEqualTo(false);
    }

    @Test
    @DisplayName("method shouldTestPassedTrue determines whether the test is passed")
    void shouldTestPassedTrue() {
        assertThat(answerService.testPassed(NUMBER_CORRECT_ANSWERS_FOR_TEST_PASSED)).isEqualTo(true);
    }

    @Test
    @DisplayName("method shouldTestPassedTrue determines whether the test is not passed")
    void shouldTestPassedFalse() {
        assertThat(answerService.testPassed(NUMBER_CORRECT_ANSWERS_FOR_TEST_NOT_PASSED)).isEqualTo(false);
    }

}
