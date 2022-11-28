package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("List of questions ")
class QuestionDaoImplTest {
    private final static String FILE_CSV_NAME = "questions.csv";
    private final static int TOTAL_QUESTIONS_IN_TEST = 5;
    private QuestionDaoImpl questionDao;

    @BeforeEach
    void setUp() {
        questionDao = new QuestionDaoImpl(FILE_CSV_NAME);
    }

    @Test
    @DisplayName("must return a list of questions")
    void shouldGetQuestionList() {
        assertThat(questionDao.getQuestionList().size()).isEqualTo(TOTAL_QUESTIONS_IN_TEST);
    }

}