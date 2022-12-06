package ru.otus.spring03testingstudentsnew.service;

public interface LanguageService {
    void doPrintSelectLanguageAndGetLocale();

    void saveLanguage(int languageId);

    String getLocalString(String code);

}
