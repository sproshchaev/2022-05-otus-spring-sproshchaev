package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Класс ReadFromConsole содержит методы для чтения данных с консоли
 */
@Service
@Scope("prototype")
public class ReadFromConsole {
    private IoСonsole ioСonsole;
    private TextToConsole textToConsole;
    private CheckAnswer checkAnswer;

    /**
     * Конструктор класса с параметрами
     *
     * @param ioСonsole
     * @param textToConsole
     * @param checkAnswer
     */
    @Autowired
    public ReadFromConsole(IoСonsole ioСonsole, TextToConsole textToConsole, CheckAnswer checkAnswer) {
        this.ioСonsole = ioСonsole;
        this.textToConsole = textToConsole;
        this.checkAnswer = checkAnswer;
    }

    /**
     * Конструктор класса без параметров
     */
    public ReadFromConsole() {
    }

    /**
     * Метод readStudentsName считывает имя студента из консоли
     *
     * @return student's name
     */
    public String readStudentsName() {
        return ioСonsole.readString().toUpperCase();
    }

    /**
     * Метод readLanguageNumber считывает номер выбранного языка из консоли
     *
     * @return answer number
     */
    public int readLanguageNumber() {
        int selectedLanguageId = 0;
        boolean inputIsCorrect = false;
        while (!inputIsCorrect) {
            textToConsole.doPrintExpectedInput2();
            selectedLanguageId = ioСonsole.readInt();
            if (checkAnswer.isCorrectInputLanguage(selectedLanguageId)) {
                inputIsCorrect = true;
            } else {
                textToConsole.doPrintInvalidInput();
            }
        }
        return selectedLanguageId;
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
