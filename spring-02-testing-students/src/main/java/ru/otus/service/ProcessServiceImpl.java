package ru.otus.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProcessServiceImpl implements ProcessService {
    private final int totalQuestionsInTest;
    private final WelcomeService welcomeService;
    private final QuestionService questionService;
    private final TotalResultService resultProcessor;
    private final ResultService resultService;

    @Autowired
    public ProcessServiceImpl(@Value("${totalQuestionsInTest}") int totalQuestionsInTest,
                              WelcomeService welcomeService, QuestionService questionService,
                              TotalResultService resultProcessor, ResultService resultService) {
        this.totalQuestionsInTest = totalQuestionsInTest;
        this.welcomeService = welcomeService;
        this.questionService = questionService;
        this.resultProcessor = resultProcessor;
        this.resultService = resultService;
    }

    @Override
    public void runProcess() {
        welcomeService.doPrintWelcomeAndWaitGetYouName();
        var result = questionService.doPrintQuestionAndGetAnswers();
        resultProcessor.doPrintResult(result);
    }
}