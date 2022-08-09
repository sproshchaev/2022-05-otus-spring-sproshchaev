package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс CheckAnswerTest выполняет JUnit-тестирование методов CheckAnswer
 */
@DisplayName("Запуск тестов класса CheckAnswerTest: ")
class CheckAnswerTest {
    private CheckAnswer checkAnswer;

    @BeforeEach
    public void setUp() {
        checkAnswer = new CheckAnswer();
        checkAnswer.setAnswerOptionOne(1);
        checkAnswer.setAnswerOptionTwo(2);
        checkAnswer.setAnswerOptionThree(3);
        checkAnswer.setNumberCorrectAnswers(3);
    }

    /**
     * success isCorrectInput
     */
    @DisplayName("тестирование метода isCorrectInput")
    @Test
    void CheckAnswer_isCorrectInput_ExpectedResult_1() {
        assertThat(checkAnswer.isCorrectInput(1)).isEqualTo(true);
    }

    /**
     * success isCorrectInput
     */
    @DisplayName("тестирование метода isCorrectInput 2")
    @Test
    void CheckAnswer_isCorrectInput_ExpectedResult_2() {
        assertThat(checkAnswer.isCorrectInput(2)).isEqualTo(true);
    }

    /**
     * success isCorrectInput
     */
    @DisplayName("тестирование метода isCorrectInput 3")
    @Test
    void CheckAnswer_isCorrectInput_ExpectedResult_3() {
        assertThat(checkAnswer.isCorrectInput(3)).isEqualTo(true);
    }

    /**
     * fail isCorrectInput
     */
    @DisplayName("тестирование метода isCorrectInput4")
    @Test
    void CheckAnswer_isCorrectInput_ExpectedResult_4() {
        assertThat(checkAnswer.isCorrectInput(4)).isEqualTo(false);
    }

    /**
     * success testPassed
     */
    @DisplayName("тестирование метода testPassed")
    @Test
    void CheckAnswer_TestPassed_ExpectedResult_True() {
        assertThat(checkAnswer.testPassed(3)).isEqualTo(true);
    }

    /**
     * fail testPassed
     */
    @DisplayName("тестирование метода testPassed 2")
    @Test
    void CheckAnswer_TestPassed_ExpectedResult_False() {
        assertThat(checkAnswer.testPassed(1)).isEqualTo(false);
    }

}