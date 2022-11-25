package ru.otus.pojo;

public class Answer {
    private final int id;
    private final String textAnswer;
    private final boolean rightAnswer;

    public Answer(int id, String textAnswer, boolean rightAnswer) {
        this.id = id;
        this.textAnswer = textAnswer;
        this.rightAnswer = rightAnswer;
    }

    public int getId() {
        return id;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    @Override
    public String toString() {
        return "AnswerService{" +
                "textAnswer='" + textAnswer + '\'' +
                ", rightAnswer=" + rightAnswer +
                '}';
    }

}
