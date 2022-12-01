package ru.otus.spring03testingstudentsnew.pojo;

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
