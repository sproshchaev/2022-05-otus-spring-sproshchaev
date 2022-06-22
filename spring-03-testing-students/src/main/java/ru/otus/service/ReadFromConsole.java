package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Класс ReadFromConsole содержит методы для чтения данных с консоли
 */
@Component
public class ReadFromConsole {
    //@Autowired
    IoСonsole ioСonsole;
    //@Autowired
    TextToConsole textToConsole;
    //@Autowired
    CheckAnswer checkAnswer;

    @Autowired
    public ReadFromConsole(IoСonsole ioСonsole, TextToConsole textToConsole, CheckAnswer checkAnswer) {
        this.ioСonsole = ioСonsole;
        this.textToConsole = textToConsole;
        this.checkAnswer = checkAnswer;
    }

    public ReadFromConsole() {
    }

    /**
     * Метод readStudentsName считывает имя студента из консоли
     *
     * @return student's name
     */
    public String readStudentsName() {
        return ioСonsole.readString();
    }

    /**
     * Метод readAnswerNumber считывает номер ответа из консоли
     *
     * @return answer number
     */
    public int readAnswerNumber() {
        int selectedAnswerId = 0;
        boolean inputIsCorrect = false;
        while (!inputIsCorrect) {
            textToConsole.doPrintExpectedInput();
            selectedAnswerId = ioСonsole.readInt();
            if (checkAnswer.isCorrectInput(selectedAnswerId)) {
                inputIsCorrect = true;
            } else {
                textToConsole.doPrintInvalidInput();
            }
        }
        return selectedAnswerId;
    }

    public void setIoСonsole(IoСonsole ioСonsole) {
        this.ioСonsole = ioСonsole;
    }

    public void setCheckAnswer(CheckAnswer checkAnswer) {
        this.checkAnswer = checkAnswer;
    }

    public void setTextToConsole(TextToConsole textToConsole) {
        this.textToConsole = textToConsole;
    }
}
