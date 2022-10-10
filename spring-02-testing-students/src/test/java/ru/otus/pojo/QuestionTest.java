package ru.otus.pojo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("QuestionTest is testing ")
class QuestionTest {

    private final static int QUESTION_ID = 1;
    private final static String QUESTION_QUESTION_TEXT = "An object is an instance:";

    private final static int LIST_ANSWER_SIZE = 3;
    private final static String QUESTION_LIST_ANSWER_1 = "programs";
    private final static String QUESTION_LIST_ANSWER_2 = "class";
    private final static String QUESTION_LIST_ANSWER_3 = "method";
    private final static int QUESTION_RIGHT_ANSWER = 2;

    @Test
    @DisplayName("the method should return the question id")
    void shouldGetQuestionId() {
        List<String> listAnswer = new ArrayList<>();
        listAnswer.add(QUESTION_LIST_ANSWER_1);
        listAnswer.add(QUESTION_LIST_ANSWER_2);
        listAnswer.add(QUESTION_LIST_ANSWER_3);
        Question expectedQuestion = new Question(QUESTION_ID, QUESTION_QUESTION_TEXT, listAnswer, QUESTION_RIGHT_ANSWER);
        assertThat(expectedQuestion.getQuestionId()).isEqualTo(QUESTION_ID);
    }

    @Test
    @DisplayName("the method should return the question text")
    void shouldGetQuestionText() {
        List<String> listAnswer = new ArrayList<>();
        listAnswer.add(QUESTION_LIST_ANSWER_1);
        listAnswer.add(QUESTION_LIST_ANSWER_2);
        listAnswer.add(QUESTION_LIST_ANSWER_3);
        Question expectedQuestion = new Question(QUESTION_ID, QUESTION_QUESTION_TEXT, listAnswer, QUESTION_RIGHT_ANSWER);
        assertThat(expectedQuestion.getQuestionText()).isEqualTo(QUESTION_QUESTION_TEXT);
    }

    @Test
    @DisplayName("the method should return a list of answers")
    void shouldGetListAnswer() {
        List<String> listAnswer = new ArrayList<>();
        listAnswer.add(QUESTION_LIST_ANSWER_1);
        listAnswer.add(QUESTION_LIST_ANSWER_2);
        listAnswer.add(QUESTION_LIST_ANSWER_3);
        Question expectedQuestion = new Question(QUESTION_ID, QUESTION_QUESTION_TEXT, listAnswer, QUESTION_RIGHT_ANSWER);
        assertThat(expectedQuestion.getListAnswer().size()).isEqualTo(LIST_ANSWER_SIZE);
    }

    @Test
    @DisplayName("the method should return the correct answer number")
    void shouldGetRightAnswer() {
        List<String> listAnswer = new ArrayList<>();
        listAnswer.add(QUESTION_LIST_ANSWER_1);
        listAnswer.add(QUESTION_LIST_ANSWER_2);
        listAnswer.add(QUESTION_LIST_ANSWER_3);
        Question expectedQuestion = new Question(QUESTION_ID, QUESTION_QUESTION_TEXT, listAnswer, QUESTION_RIGHT_ANSWER);
        assertThat(expectedQuestion.getRightAnswer()).isEqualTo(QUESTION_RIGHT_ANSWER);
    }

}
