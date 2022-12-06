package ru.otus.spring03testingstudentsnew.pojo;

import org.springframework.stereotype.Component;

@Component
public class Language {
    private int languageId;

    private String isoCode;

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", isoCode='" + isoCode + '\'' +
                '}';
    }
}
