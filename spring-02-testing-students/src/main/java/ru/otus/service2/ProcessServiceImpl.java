package ru.otus.service2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.processors.ShowQuestionProcessor;
import ru.otus.processors.ShowWelcomeProcessor;
import ru.otus.service.AnswerService;
import ru.otus.service.IoService;

/**
 * Класс ProcessServiceImpl содержит методы для тестирования студентов
 */
@Service
public class ProcessServiceImpl implements ProcessService {

    private final int totalQuestionsInTest;
    private final AnswerService answerService;
    private final IoService ioService;
    private final ShowWelcomeProcessor showWelcomeProcessor;
    private final ShowQuestionProcessor showQuestionProcessor;
    private String studentsName;
    private int countCorrectAnswers;

    @Autowired
    public ProcessServiceImpl(@Value("${totalQuestionsInTest}") int totalQuestionsInTest, AnswerService answerService,
                              IoService ioService, ShowWelcomeProcessor showWelcomeProcessor,
                              ShowQuestionProcessor showQuestionProcessor) {
        this.totalQuestionsInTest = totalQuestionsInTest;
        this.answerService = answerService;
        this.ioService = ioService;
        this.showWelcomeProcessor = showWelcomeProcessor;
        this.showQuestionProcessor = showQuestionProcessor;
    }

    /**
     * Метод runTest() выполняет тестирование студента
     */
    @Override
    public void runProcess() {
        //ioService.doPrintWelcomeAndWaitGetYouName();
        // studentsName = ioService.readStudentsName();

        studentsName = showWelcomeProcessor.doPrintWelcomeAndWaitGetYouName();

        int selectedAnswerId = 0;

        for (int testNumber = 0; testNumber < totalQuestionsInTest; testNumber++) {

            selectedAnswerId = showQuestionProcessor.doPrintQuestionAndAnswers(testNumber);

/*
            ioService.doPrintQuestionAndAnswers(testNumber);
            selectedAnswerId = ioService.readAnswerNumber(testNumber);
            if (answerService.checkCorrectAnswer(testNumber, selectedAnswerId)) {
                countCorrectAnswers++;
            }
*/
        }
//        ioService.doPrintTestResults(studentsName, countCorrectAnswers);

    }
}
