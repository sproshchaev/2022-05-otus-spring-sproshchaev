package ru.otus.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Класс TestsForStudents содержит методы для тестирования студентов
 */
@Service
public class TestsForStudents implements Tests {
    private int totalQuestionsInTest;
    private int countCorrectAnswers;
    private TextToConsole textToConsole;
    private ReadFromConsole readFromConsole;
    private CheckAnswer checkAnswer;
    private Localization localization;
    private Identification identification;

    /**
     * Конструктор класса с параметрами
     *
     * @param totalQuestionsInTest
     * @param textToConsole
     * @param readFromConsole
     * @param checkAnswer
     */
    @Autowired
    public TestsForStudents(@Value("${totalQuestionsInTest}") int totalQuestionsInTest, TextToConsole textToConsole,
                            ReadFromConsole readFromConsole, CheckAnswer checkAnswer, Localization localization,
                            Identification identification) {
        this.totalQuestionsInTest = totalQuestionsInTest;
        this.textToConsole = textToConsole;
        this.readFromConsole = readFromConsole;
        this.checkAnswer = checkAnswer;
        this.localization = localization;
        this.identification = identification;
    }

    /**
     * Метод runTest() выполняет тестирование студента
     */
    @Override
    public void runTest() {
        localization.setLanguage();
        identification.getStudentName();
        int selectedAnswerId;
        for (int testNumber = 1; testNumber <= totalQuestionsInTest; testNumber++) {
            textToConsole.doPrintQuestionAndAnswers(testNumber);
            selectedAnswerId = readFromConsole.readAnswerNumber();
            if (checkAnswer.сheckСorrectAnswer(testNumber, selectedAnswerId)) {
                countCorrectAnswers++;
            }
        }
        textToConsole.doPrintTestResults(countCorrectAnswers);
    }

}
