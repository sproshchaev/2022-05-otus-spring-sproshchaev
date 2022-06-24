package ru.otus.pojo;

import org.springframework.stereotype.Component;
import ru.otus.enums.IsoCode;
import ru.otus.enums.LanguageEnglishName;

/**
 * Класс Language - POJO-класс, содержащий языки вывода информации
 * Поля класса IsoCode и EnglishName определяются по ISO 639-2 Language Code List https://bit.ly/3OC8BWj
 */
@Component
public class Language {
    private int id;
    private IsoCode isoCode;
    private LanguageEnglishName languageEnglishName;

    /**
     * Конструктор класса
     * При создании объекта берет первые значения из перечислений IsoCode и LanguageEnglishName
     */
    public Language() {
        this.id = 1;
        this.isoCode = IsoCode.values()[0];  // IsoCode.en;
        this.languageEnglishName = LanguageEnglishName.values()[0]; // LanguageEnglishName.English;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsoCode(IsoCode isoCode) {
        this.isoCode = isoCode;
    }

    public void setLanguageEnglishName(LanguageEnglishName languageEnglishName) {
        this.languageEnglishName = languageEnglishName;
    }

    public int getId() {
        return id;
    }

    public IsoCode getIsoCode() {
        return isoCode;
    }

    public LanguageEnglishName getLanguageEnglishName() {
        return languageEnglishName;
    }

    /**
     * Метод toString() выводит значения полей класса
     *
     * @return
     */
    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", IsoCode='" + isoCode + '\'' +
                ", EnglishName='" + languageEnglishName + '\'' +
                '}';
    }

}
