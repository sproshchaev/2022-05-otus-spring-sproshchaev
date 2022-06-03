package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс ReadingQuestionsFileTest содержит unit-тесты класса ReadingQuestionsFile
 */
@DisplayName("Запуск тестов класса ReadingQuestionsFileTest: ")
public class ReadingQuestionsFileTest {

    /**
     * Метод successGetQuestionById выполняет unit-тестирование метода getQuestionById
     */
    @DisplayName("тестирование метода getQuestionById")
    @Test
    public void successGetQuestionById() {
        ReadingQuestionsFile readingQuestionsFile = new ReadingQuestionsFile();
        readingQuestionsFile.setFileCsvName("questions.csv");
        String resultExpected = "Question{questionNumber=1, questionText='An object is an instance:', firstAnswer='programs', secondAnswer='class', thirdAnswer='method', rightAnswer=2}";
        assertThat(readingQuestionsFile.getQuestionById(1).toString()).isEqualTo(resultExpected);
    }
}