package ru.otus.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.pojo.Result;

@Service
public class ProcessServiceImpl implements ProcessService {
    private final int totalQuestionsInTest;
    private final WelcomeService welcomeService;
    private final QuestionService questionService;
    private final TotalResultService resultProcessor;
    private final ResultService resultService;

    @Autowired
    public ProcessServiceImpl(@Value("${totalQuestionsInTest}") int totalQuestionsInTest,
                              WelcomeServiceImpl welcomeService, QuestionServiceImpl questionService,
                              TotalResultServiceImpl resultProcessor, ResultService resultService) {
        this.totalQuestionsInTest = totalQuestionsInTest;
        this.welcomeService = welcomeService;
        this.questionService = questionService;
        this.resultProcessor = resultProcessor;
        this.resultService = resultService;
    }

    @Override
    public void runProcess() {
        welcomeService.doPrintWelcomeAndWaitGetYouName();
        int selectedAnswerId, countRunTest = 0;
        var result = new Result();
        for (int testNumber = 0; testNumber < totalQuestionsInTest; testNumber++) {
            selectedAnswerId = questionService.doPrintQuestionAndGetAnswers(testNumber);
            resultService.saveAnswerResult(testNumber, selectedAnswerId, result);
            countRunTest++;
        }
        resultProcessor.doPrintResult(result);
    }
}
