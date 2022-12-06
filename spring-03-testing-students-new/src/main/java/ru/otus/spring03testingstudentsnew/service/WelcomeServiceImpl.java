package ru.otus.spring03testingstudentsnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WelcomeServiceImpl implements WelcomeService {
    private final IOService ioService;
    private final LanguageService languageService;

    @Autowired
    public WelcomeServiceImpl(IOService ioService, LanguageService languageService) {
        this.ioService = ioService;
        this.languageService = languageService;
    }

    @Override
    public void doPrintWelcomeAndGetYouName() {
        ioService.readStringWithPrompt(languageService.getLocalString("heading.testsName") + "\n"
                + languageService.getLocalString("heading.welcome"));
    }

}
