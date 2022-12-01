package ru.otus.spring03testingstudentsnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WelcomeServiceImpl implements WelcomeService {
    private final IOService ioService;

    @Autowired
    public WelcomeServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void doPrintWelcomeAndWaitGetYouName() {
        ioService.readStringWithPrompt("Testing on the basics of Java. \nPlease enter your name:");
    }

}
