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
    @Value("${totalQuestionsInTest}")
    private int totalQuestionsInTest;
    private int countCorrectAnswers;
    @Autowired
    TextToConsole textToConsole;
    @Autowired
    ReadFromConsole readFromConsole;
    @Autowired
    CheckAnswer checkAnswer;

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