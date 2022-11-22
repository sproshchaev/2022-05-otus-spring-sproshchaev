package ru.otus.pojo;

import java.util.List;
import java.util.Objects;

public class Question {
    private final int id;
    private final String textQuestion;
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
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", textQuestion='" + textQuestion + '\'' +
                ", listAnswer=" + listAnswer +
                '}';
    }

}
