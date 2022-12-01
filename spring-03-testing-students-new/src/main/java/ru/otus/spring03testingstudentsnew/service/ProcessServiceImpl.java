package ru.otus.spring03testingstudentsnew.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessServiceImpl implements ProcessService {
    private final WelcomeService welcomeService;
    private final QuestionService questionService;
    private final TotalResultService resultProcessor;

    @Autowired
    public ProcessServiceImpl(WelcomeService welcomeService, QuestionService questionService,
                              TotalResultService resultProcessor) {
        this.welcomeService = welcomeService;
        this.questionService = questionService;
        this.resultProcessor = resultProcessor;
    }

    @Override
    public void runProcess() {
        welcomeService.doPrintWelcomeAndWaitGetYouName();
        var result = questionService.doPrintQuestionAndGetAnswers();
        resultProcessor.doPrintResult(result);
    }
}
