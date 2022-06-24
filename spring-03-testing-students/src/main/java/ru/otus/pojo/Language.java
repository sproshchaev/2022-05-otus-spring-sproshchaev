package ru.otus.pojo;

import java.util.Locale;

/**
 * Класс Language - POJO-класс, содержащий языки вывода информации
 * Поля класса IsoCode и EnglishName определяются по ISO 639-2 Language Code List https://bit.ly/3OC8BWj
 */
public class Language {
    private final int id;
    private final String IsoCode;
    private final String EnglishName;

    /**
     * Конструктор класса
     * @param id
     * @param isoCode
     * @param englishName
     */
    public Language(int id, String isoCode, String englishName) {
        this.id = id;
        IsoCode = isoCode;
        EnglishName = englishName;
    }

    /**
     * Метод toString() выводит знечения полей класса
     *
     * @return
     */
    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", IsoCode='" + IsoCode + '\'' +
                ", EnglishName='" + EnglishName + '\'' +
                '}';
    }
}
