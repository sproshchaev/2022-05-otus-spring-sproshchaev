package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.ReadingQuestionsFile;
import ru.otus.enums.IsoCode;
import ru.otus.enums.LanguageName;
import ru.otus.pojo.Language;

/**
 * Класс Localization
 */
@Service
public class Localization {
    private TextToConsole textToConsole;
    private ReadFromConsole readFromConsole;
    private Language language;
    private String fileCsvNameTwo;
    private ReadingQuestionsFile readingQuestionsFile;

    /**
     * Конструктор класса
     *
     * @param textToConsole
     */
    @Autowired
    public Localization(TextToConsole textToConsole, ReadFromConsole readFromConsole, Language language,
                        ReadingQuestionsFile readingQuestionsFile,
                        @Value("${fileCsvName.Two}") String fileCsvNameTwo) {
        this.textToConsole = textToConsole;
        this.readFromConsole = readFromConsole;
        this.language = language;
        this.readingQuestionsFile = readingQuestionsFile;
        this.fileCsvNameTwo = fileCsvNameTwo;
    }

    /**
     * Метод setLanguage устанавливает язык вывода информации студенту
     */
    public void setLanguage() {
        textToConsole.doPrintSelectLanguage();
        int selectedLanguageId = readFromConsole.readLanguageNumber();
        if (selectedLanguageId == 2) {
            setLanguageTwo();
        }
    }

    /**
     * Метод setLanguageTwo устанавливает второй язык из IsoCode для прохождения теста
     */
    private void setLanguageTwo() {
        language.setId(2);
        language.setIsoCode(IsoCode.values()[1]);
        language.setLanguageName(LanguageName.values()[1]);
        language.setFileCsvName(fileCsvNameTwo);
        readingQuestionsFile.setFileCsvName(fileCsvNameTwo);
    }

}
