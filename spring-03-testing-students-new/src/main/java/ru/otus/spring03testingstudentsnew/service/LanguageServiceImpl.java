package ru.otus.spring03testingstudentsnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring03testingstudentsnew.pojo.Language;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final Language language;
    private final IOService ioService;

    @Autowired
    public LanguageServiceImpl(Language language, IOService ioService) {
        this.language = language;
        this.ioService = ioService;
    }

    @Override
    public void doPrintSelectLanguageAndGetLocale() {
        int selectedLanguageId = ioService.readIntWithPrompt("Select Language:");
        language.setLanguageId(selectedLanguageId);
    }
}
