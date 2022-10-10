package ru.otus.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.Reading;

/**
 * Класс StudentsTestingService содержит методы для тестирования студентов
 */
@Service
public class StudentsTestingService implements Tests {
    private String studentsName;
    private final int totalQuestionsInTest;
    private int countCorrectAnswers;
    private final CheckAnswer checkAnswer;
    private final IoService ioService;
    private final Reading reading;

    @Autowired
    public StudentsTestingService(@Value("${totalQuestionsInTest}") int totalQuestionsInTest, CheckAnswer checkAnswer,
                                  IoService ioService, Reading reading) {
        this.totalQuestionsInTest = totalQuestionsInTest;
        this.checkAnswer = checkAnswer;
        this.ioService = ioService;
        this.reading = reading;
    }

    /**
     * Метод runTest() выполняет тестирование студента
     */
    @Override
    public void runTest() {
        ioService.doPrintWelcomeAndWaitGetYouName();
        studentsName = ioService.readStudentsName();
        int selectedAnswerId;
        for (int testNumber = 1; testNumber <= totalQuestionsInTest; testNumber++) {
            ioService.doPrintQuestionAndAnswers(testNumber);
            selectedAnswerId = ioService.readAnswerNumber(testNumber);
            if (checkAnswer.checkCorrectAnswer(testNumber, selectedAnswerId)) {
                countCorrectAnswers++;
            }
        }
        ioService.doPrintTestResults(studentsName, countCorrectAnswers);
    }
}
