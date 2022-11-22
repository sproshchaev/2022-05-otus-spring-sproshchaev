package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.QuestionDaoImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Class of the test result ")
@ExtendWith(MockitoExtension.class)
class ResultServiceImplTest {
    private final static int MIN_NUMBER_CORRECT_ANSWER_PASSED = 0;
    private final static int STRING_LENGTH_RESULT_PASSED = 11;
    private final static int MIN_NUMBER_CORRECT_ANSWER_FAILED = 3;
    private final static int STRING_LENGTH_RESULT_FAILED = 22;
    @Mock
    private QuestionDaoImpl questionDao;

    @Test
    @DisplayName("returns 'test failed'")
    void shouldReturnResultFailed() {
        var resultService = new ResultServiceImpl(MIN_NUMBER_CORRECT_ANSWER_FAILED, questionDao);
        assertThat(resultService.getResult().length()).isEqualTo(STRING_LENGTH_RESULT_FAILED);
    }

    @Test
    @DisplayName("returns 'test passed'")
    void shouldReturnResultPassed() {
        var resultService = new ResultServiceImpl(MIN_NUMBER_CORRECT_ANSWER_PASSED, questionDao);
        assertThat(resultService.getResult().length()).isEqualTo(STRING_LENGTH_RESULT_PASSED);
    }


}