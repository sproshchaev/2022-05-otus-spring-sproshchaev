package ru.otus.spring03testingstudentsnew.pojo;

import org.springframework.stereotype.Component;

@Component
public class Result {
    private int countTrueAnswer;

    public Result() {}

    public int getCountTrueAnswer() {
        return countTrueAnswer;
    }

    public void addTrueAnswer() {
        countTrueAnswer++;
    }

    @Override
    public String toString() {
        return "Result{" +
                "countTrueAnswer=" + countTrueAnswer +
                '}';
    }
}
