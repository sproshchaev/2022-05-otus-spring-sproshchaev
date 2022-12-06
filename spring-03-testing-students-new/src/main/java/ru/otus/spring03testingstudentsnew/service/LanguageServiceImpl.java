package ru.otus.spring03testingstudentsnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.otus.spring03testingstudentsnew.dao.QuestionDao;
import ru.otus.spring03testingstudentsnew.pojo.Language;

import java.util.Locale;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final String languageTwo;
    private final Language language;
    private final IOService ioService;
    private final ApplicationContext context;
    private final QuestionDao questionDao;

    @Autowired
    public LanguageServiceImpl(@Value("${language.Two}") String languageTwo, Language language, IOService ioService,
                               ApplicationContext context, QuestionDao questionDao) {
        this.languageTwo = languageTwo;
        this.language = language;
        this.ioService = ioService;
        this.context = context;
        this.questionDao = questionDao;
    }

    @Override
    public void doPrintSelectLanguageAndGetLocale() {
        int selectedLanguageId = ioService.readIntWithPrompt("Select Language: (1-en, 2-" + languageTwo + ")");
        saveLanguage(selectedLanguageId);
    }

    @Override
    public void saveLanguage(int languageId) {
        language.setIsoCode(languageId == 1 ? "en" : languageTwo);
        questionDao.setFileCsvNameCurrent(languageId);
    }

    @Override
    public String getLocalString(String code) {
        return context.getMessage(code, null, Locale.forLanguageTag(language.getIsoCode()));
    }

}

