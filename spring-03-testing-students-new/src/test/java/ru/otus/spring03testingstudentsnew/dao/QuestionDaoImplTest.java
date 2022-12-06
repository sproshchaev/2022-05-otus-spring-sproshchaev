package ru.otus.spring03testingstudentsnew.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring03testingstudentsnew.service.LanguageService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("List of questions ")
class QuestionDaoImplTest {
    private final static int TOTAL_QUESTIONS_IN_TEST = 5;
    private final static int LANG1 = 1;
    private final static String LANGUAGE1_QUESTION1 = "An object is an instance";
    private final static int LANG2 = 2;
    private final static String LANGUAGE2_QUESTION1 = "Объект - это экземпляр";

    @Autowired
    QuestionDao questionDao;
    @Autowired
    LanguageService languageService;

    @Test
    @DisplayName("must return a list of questions in lang. 1")
    void shouldGetQuestionListLangOne() {
        languageService.saveLanguage(LANG1);
        assertEquals(questionDao.getQuestionList().size(), TOTAL_QUESTIONS_IN_TEST);
    }

    @Test
    @DisplayName("must return question 1 in lang. 1")
    void shouldGetQuestionListLangOneQuestion1() {
        languageService.saveLanguage(LANG1);
        assertThat(questionDao.getQuestionList().get(0).getTextQuestion()).isEqualTo(LANGUAGE1_QUESTION1);
    }

    @Test
    @DisplayName("must return a list of questions in lang. 2")
    void shouldGetQuestionListLangTwo() {
        languageService.saveLanguage(LANG2);
        assertEquals(questionDao.getQuestionList().size(), TOTAL_QUESTIONS_IN_TEST);
    }

    @Test
    @DisplayName("must return question 1 in lang. 2")
    void shouldGetQuestionListLangTwoQuestion1() {
        languageService.saveLanguage(LANG2);
        assertThat(questionDao.getQuestionList().get(0).getTextQuestion()).isEqualTo(LANGUAGE2_QUESTION1);
    }

}