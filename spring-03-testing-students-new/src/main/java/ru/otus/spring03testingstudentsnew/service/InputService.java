package ru.otus.spring03testingstudentsnew.service;

public interface InputService {
    int readInt();

    int readIntWithPrompt(String prompt);

    String readStringWithPrompt(String prompt);

}
