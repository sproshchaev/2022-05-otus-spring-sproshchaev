package ru.otus.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Класс TestsForStudents содержит методы для тестирования студентов
 */
@Component
public class TestsForStudents implements Tests {
    private String studentsName;
    private int totalQuestionsInTest;
    private int countCorrectAnswers;
    TextToConsole textToConsole;
    ReadFromConsole readFromConsole;
    CheckAnswer checkAnswer;

    /**
     * Конструктор класса с параметрами
     *
     * @param totalQuestionsInTest
     * @param textToConsole
     * @param readFromConsole
     * @param checkAnswer
     */
    @Autowired
    public TestsForStudents(@Value("${totalQuestionsInTest}") int totalQuestionsInTest,
                            TextToConsole textToConsole,
                            ReadFromConsole readFromConsole,
                            CheckAnswer checkAnswer) {
        this.totalQuestionsInTest = totalQuestionsInTest;
        this.textToConsole = textToConsole;
        this.readFromConsole = readFromConsole;
        this.checkAnswer = checkAnswer;
    }

    /**
     * Метод runTest() выполняет тестирование студента
     */
    @Override
    public void runTest() {
        textToConsole.doPrintWelcomeAndWaitGetYouName();
        studentsName = readFromConsole.readStudentsName();
        int selectedAnswerId;
        for (int testNumber = 1; testNumber <= totalQuestionsInTest; testNumber++) {
            textToConsole.doPrintQuestionAndAnswers(testNumber);
            selectedAnswerId = readFromConsole.readAnswerNumber();
            if (checkAnswer.сheckСorrectAnswer(testNumber, selectedAnswerId)) {
                countCorrectAnswers++;
            }
        }
        textToConsole.doPrintTestResults(studentsName, countCorrectAnswers);
    }

}
