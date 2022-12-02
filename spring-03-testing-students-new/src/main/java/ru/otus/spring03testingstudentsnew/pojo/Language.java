package ru.otus.spring03testingstudentsnew.pojo;

import org.springframework.stereotype.Component;

@Component
public class Language {
    private int languageId;

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                '}';
    }
}
