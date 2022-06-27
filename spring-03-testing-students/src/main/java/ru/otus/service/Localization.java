package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.enums.IsoCode;
import ru.otus.enums.LanguageEnglishName;
import ru.otus.pojo.Language;

/**
 * Класс Localization
 */
@Service
public class Localization {
    private TextToConsole textToConsole;
    private ReadFromConsole readFromConsole;
    private Language language;

    /**
     * Конструктор класса
     *
     * @param textToConsole
     */
    @Autowired
    public Localization(TextToConsole textToConsole, ReadFromConsole readFromConsole, Language language) {
        this.textToConsole = textToConsole;
        this.readFromConsole = readFromConsole;
        this.language = language;
    }

    /**
     * Метод setLanguage устанавливает язык вывода информации студенту
     */
    public void setLanguage() {

        textToConsole.doPrintSelectLanguage();
        int selectedLanguageId = readFromConsole.readLanguageNumber();

        if (selectedLanguageId == 2) {
            setRu(); // сделать set langopt1 и 2
        }

    }

    /**
     * Метод setRu устанавливает RU язык по умолчанию (отрефакторить)
     */
    private void setRu() {
        language.setId(2);
        language.setIsoCode(IsoCode.ru);
        language.setLanguageEnglishName(LanguageEnglishName.Russian);
    }

}
