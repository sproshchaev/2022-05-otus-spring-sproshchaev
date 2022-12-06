package ru.otus.spring03testingstudentsnew.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring03testingstudentsnew.dao.QuestionDao;
import ru.otus.spring03testingstudentsnew.pojo.Result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DisplayName("Class of the test result ")
class ResultServiceImplTest {
    private final static int LANG1 = 1;
    private final static String STRING_RESULT_PASSED_LANG1 = "Test passed successfully!";
    private final static String STRING_RESULT_FAILED_LANG1 = "Test failed!";
    private final static int LANG2 = 2;
    private final static String STRING_RESULT_PASSED_LANG2 = "\u0422\u0435\u0441\u0442 \u043F\u0440\u043E\u0439\u0434\u0435\u043D \u0443\u0441\u043F\u0435\u0448\u043D\u043E";
    private final static String STRING_RESULT_FAILED_LANG2 = "\u0422\u0435\u0441\u0442 \u043D\u0435 \u043F\u0440\u043E\u0439\u0434\u0435\u043D";

    @Autowired
    ResultService resultService;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    Result result;
    @Autowired
    LanguageService languageService;

    @Test
    @DisplayName("returns 'Test failed!'")
    void shouldReturnResultFailedLang1() {
        languageService.saveLanguage(LANG1);
        resultService.saveAnswerResult(questionDao.getQuestionList().get(0), 2);
        assertThat(resultService.getResult()).isEqualTo(STRING_RESULT_FAILED_LANG1);
    }

    @Test
    @DisplayName("returns 'Test passed successfully!'")
    void shouldReturnResultPassedLang1() {
        languageService.saveLanguage(LANG1);
        resultService.saveAnswerResult(questionDao.getQuestionList().get(0), 2);
        resultService.saveAnswerResult(questionDao.getQuestionList().get(0), 2);
        resultService.saveAnswerResult(questionDao.getQuestionList().get(0), 2);
        assertThat(resultService.getResult()).isEqualTo(STRING_RESULT_PASSED_LANG1);
    }

    @Test
    @DisplayName("returns 'Test failed!'")
    void shouldReturnResultFailedLang2() {
        languageService.saveLanguage(LANG2);
        resultService.saveAnswerResult(questionDao.getQuestionList().get(0), 2);
        assertThat(resultService.getResult()).isEqualTo(STRING_RESULT_FAILED_LANG2);
    }

    @Test
    @DisplayName("returns 'Test passed successfully!'")
    void shouldReturnResultPassedLang2() {
        languageService.saveLanguage(LANG2);
        resultService.saveAnswerResult(questionDao.getQuestionList().get(0), 2);
        resultService.saveAnswerResult(questionDao.getQuestionList().get(0), 2);
        resultService.saveAnswerResult(questionDao.getQuestionList().get(0), 2);
        assertThat(resultService.getResult()).isEqualTo(STRING_RESULT_PASSED_LANG2);
    }

}