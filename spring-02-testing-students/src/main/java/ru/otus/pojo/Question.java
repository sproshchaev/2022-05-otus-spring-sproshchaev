package ru.otus.pojo;

import java.util.List;
import java.util.Objects;

public class Question {

    /**
     * Поле "Номер вопроса"
     */
    private final int id;

    /**
     * Поле "Текст вопроса"
     */
    private final String textQuestion;

    /**
     * Поле "Варианты ответов"
     */
    private final List<Answer> listAnswer;

    public Question(int id, String textQuestion, List<Answer> listAnswer) {
        this.id = id;
        this.textQuestion = textQuestion;
        this.listAnswer = listAnswer;
    }

    public int getId() {
        return id;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public List<Answer> getListAnswer() {
        return listAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && textQuestion.equals(question.textQuestion) && listAnswer.equals(question.listAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textQuestion, listAnswer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", textQuestion='" + textQuestion + '\'' +
                ", listAnswer=" + listAnswer +
                '}';
    }

}
