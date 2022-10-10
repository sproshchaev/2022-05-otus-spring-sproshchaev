package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.pojo.Question;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("ReadingQuestionsFileFileTest is testing ")
class ReadingQuestionsFileTest {
    private final static int EXPECTED_COUNT_QUESTIONS = 5;
    private final static int QUESTION_ID = 1;
    private final static String QUESTION_QUESTION_TEXT = "An object is an instance:";
    private final static String QUESTION_LIST_ANSWER_1 = "programs";
    private final static String QUESTION_LIST_ANSWER_2 = "class";
    private final static String QUESTION_LIST_ANSWER_3 = "method";
    private final static int QUESTION_RIGHT_ANSWER = 2;

    @Test
    @DisplayName("method mustFillQuestionList must fill list of test questions")
    void shouldFillQuestionList() {
        ReadingQuestionsFile readingQuestionsFile = new ReadingQuestionsFile("questions.csv");
        assertThat(readingQuestionsFile.fillQuestionList().size()).isEqualTo(EXPECTED_COUNT_QUESTIONS);
    }

    @Test
    @DisplayName("method mustGetQuestionById must returns an instance of the Question class")
    void shouldGetQuestionById() {
        ReadingQuestionsFile readingQuestionsFile = new ReadingQuestionsFile("questions.csv");
        List<String> listAnswer = new ArrayList<>();
        listAnswer.add(QUESTION_LIST_ANSWER_1);
        listAnswer.add(QUESTION_LIST_ANSWER_2);
        listAnswer.add(QUESTION_LIST_ANSWER_3);
        Question expectedQuestion = new Question(QUESTION_ID, QUESTION_QUESTION_TEXT, listAnswer, QUESTION_RIGHT_ANSWER);
        assertThat(readingQuestionsFile.getQuestionById(QUESTION_ID)).isEqualTo(expectedQuestion);
    }
}
