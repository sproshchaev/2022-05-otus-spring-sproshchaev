package ru.otus.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProcessServiceImpl implements ProcessService {
    private final int totalQuestionsInTest;
    private final WelcomeServiceImpl welcomeServiceImpl;
    private final QuestionServiceImpl questionServiceImpl;
    private final TotalResultServiceImpl resultProcessor;
    private final ResultService resultService;

    @Autowired
    public ProcessServiceImpl(@Value("${totalQuestionsInTest}") int totalQuestionsInTest,
                              WelcomeServiceImpl welcomeServiceImpl, QuestionServiceImpl questionServiceImpl,
                              TotalResultServiceImpl resultProcessor, ResultService resultService) {
        this.totalQuestionsInTest = totalQuestionsInTest;
        this.welcomeServiceImpl = welcomeServiceImpl;
        this.questionServiceImpl = questionServiceImpl;
        this.resultProcessor = resultProcessor;
        this.resultService = resultService;
    }

    @Override
    public int runProcess() {
        welcomeServiceImpl.doPrintWelcomeAndWaitGetYouName();
        int selectedAnswerId, countRunTest = 0;
        for (int testNumber = 0; testNumber < totalQuestionsInTest; testNumber++) {
            selectedAnswerId = questionServiceImpl.doPrintQuestionAndGetAnswers(testNumber);
            resultService.saveAnswerResult(testNumber, selectedAnswerId);
            countRunTest++;
        }
        resultProcessor.doPrintResult();
        return countRunTest;
    }
}
