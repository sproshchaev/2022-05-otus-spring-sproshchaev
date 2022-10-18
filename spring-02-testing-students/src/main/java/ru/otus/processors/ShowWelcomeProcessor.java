package ru.otus.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.service2.IOService;

@Component
public class ShowWelcomeProcessor {

    private final IOService ioService;

    @Autowired
    public ShowWelcomeProcessor(IOService ioService) {
        this.ioService = ioService;
    }

    public String doPrintWelcomeAndWaitGetYouName() {
        return ioService.readStringWithPrompt("Testing on the basics of Java. \nPlease enter your name:");
    }

}
