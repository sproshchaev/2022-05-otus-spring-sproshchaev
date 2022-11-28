package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.pojo.Result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Class of the test result ")
class ResultServiceImplTest {
    private final static int MIN_NUMBER_CORRECT_ANSWER_PASSED = 1;
    private final static int STRING_LENGTH_RESULT_PASSED = 11;
    private final static int STRING_LENGTH_RESULT_FAILED = 22;

    @Test
    @DisplayName("returns 'test failed'")
    void shouldReturnResultFailed() {
        var resultService = new ResultServiceImpl(MIN_NUMBER_CORRECT_ANSWER_PASSED);
        var result = new Result();
        assertThat(resultService.getResult(result).length()).isEqualTo(STRING_LENGTH_RESULT_FAILED);
    }

    @Test
    @DisplayName("returns 'test passed'")
    void shouldReturnResultPassed() {
        var resultService = new ResultServiceImpl(MIN_NUMBER_CORRECT_ANSWER_PASSED);
        var result = new Result();
        result.addTrueAnswer();
        System.out.println(result.getCountTrueAnswer());
        assertThat(resultService.getResult(result).length()).isEqualTo(STRING_LENGTH_RESULT_PASSED);
    }

}