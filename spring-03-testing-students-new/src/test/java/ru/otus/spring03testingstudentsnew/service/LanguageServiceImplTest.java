package ru.otus.spring03testingstudentsnew.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring03testingstudentsnew.pojo.Language;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Language tests ")
class LanguageServiceImplTest {
    private final static int LANG1 = 1;
    private final static String LANG1_ISO_CODE = "en";
    private final static int LANG2 = 2;
    private final static String LANG2_ISO_CODE = "ru";
    private final static String HEADING_TESTS_NAME = "heading.testsName";
    private final static String HEADING_TESTS_NAME_LANG1 = "Testing on the basics of Java";
    private final static String HEADING_TESTS_NAME_LANG2 = "\u0422\u0435\u0441\u0442\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435 \u043F\u043E \u043E\u0441\u043D\u043E\u0432\u0430\u043C Java";

    @Autowired
    Language language;
    @Autowired
    LanguageService languageService;

    @Test
    @DisplayName("must save the first language")
    void shouldSaveLanguageLang1() {
        languageService.saveLanguage(LANG1);
        assertEquals(LANG1_ISO_CODE, language.getIsoCode());
    }

    @Test
    @DisplayName("must get local string in first language")
    void shouldGetLocalStringLang1() {
        languageService.saveLanguage(LANG1);
        assertEquals(languageService.getLocalString(HEADING_TESTS_NAME), HEADING_TESTS_NAME_LANG1);
    }

    @Test
    @DisplayName("must save the second language")
    void shouldSaveLanguageLang2() {
        languageService.saveLanguage(LANG2);
        assertEquals(LANG2_ISO_CODE, language.getIsoCode());
    }

    @Test
    @DisplayName("must get local string in second language")
    void shouldGetLocalStringLang2() {
        languageService.saveLanguage(LANG2);
        assertEquals(languageService.getLocalString(HEADING_TESTS_NAME), HEADING_TESTS_NAME_LANG2);
    }

}