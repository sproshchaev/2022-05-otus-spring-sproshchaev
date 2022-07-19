package ru.otus.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.enums.IsoCode;
import ru.otus.enums.LanguageName;

/**
 * Класс Language - POJO-класс, содержащий языки вывода информации
 * Поля класса IsoCode и EnglishName определяются по ISO 639-2 Language Code List https://bit.ly/3OC8BWj
 */
@Component
public class Language {
    private int id;
    /**
     * Поле класса isoCode содержит код языка по формату ISO: en, ru
     */
    private IsoCode isoCode;
    /**
     * Поле класса languageName содержит наименование языка на английском языке: English, Russian
     */
    private LanguageName languageName;
    /**
     * Поле класса fileCsvName содержит имя csv-файла с вопросами тестов на выбранном языке
     */
    private String fileCsvName;

    /**
     * Конструктор класса
     * При создании объекта берет первые значения из перечислений IsoCode и LanguageName
     */
    @Autowired
    public Language(@Value("${fileCsvName.One}") String fileCsvName) {
        this.id = 1;
        this.isoCode = IsoCode.values()[0];
        this.languageName = LanguageName.values()[0];
        this.fileCsvName = fileCsvName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IsoCode getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(IsoCode isoCode) {
        this.isoCode = isoCode;
    }

    public LanguageName getLanguageName() {
        return languageName;
    }

    public void setLanguageName(LanguageName languageName) {
        this.languageName = languageName;
    }

    public String getFileCsvName() {
        return fileCsvName;
    }

    public void setFileCsvName(String fileCsvName) {
        this.fileCsvName = fileCsvName;
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
                ", isoCode=" + isoCode +
                ", languageName=" + languageName +
                ", fileCsvName='" + fileCsvName + '\'' +
                '}';
    }

}
