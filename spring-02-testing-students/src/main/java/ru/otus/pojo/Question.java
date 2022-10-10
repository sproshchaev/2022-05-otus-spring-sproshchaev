package ru.otus.pojo;

import java.util.List;
import java.util.Objects;

/**
 * Класс Question - POJO-класс, содержащий вопрос теста
 */
public class Question {

    /**
     * Поле "Номер вопроса"
     */
    private final int questionId;

    /**
     * Поле "Текст вопроса"
     */
    private final String questionText;

    /**
     * Поле "Варианты ответов"
     */
    private final List<String> listAnswer;

    /**
     * Поле "Номер правильного варианта ответа"
     */
    private final int rightAnswer;

    public Question(int questionId, String questionText, List<String> listAnswer, int rightAnswer) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.listAnswer = listAnswer;
        this.rightAnswer = rightAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getListAnswer() {
        return listAnswer;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    /**
     * Переопределение метода toString()
     *
     * @return
     */
    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionText='" + questionText + '\'' +
                ", listAnswer=" + listAnswer +
                ", rightAnswer=" + rightAnswer +
                '}';
    }

    /**
     * Переопределение метода equals()
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionId == question.questionId && rightAnswer == question.rightAnswer && questionText.equals(question.questionText) && listAnswer.equals(question.listAnswer);
    }

    /**
     * Переопределение метода hashCode()
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(questionId, questionText, listAnswer, rightAnswer);
    }
}
