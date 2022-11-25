package ru.otus.pojo;

public class Result {
    private int countTrueAnswer;

    public Result() {}

    public int getCountTrueAnswer() {
        return countTrueAnswer;
    }

    public void addTrueAnswer() {
        countTrueAnswer++;
    }

}
