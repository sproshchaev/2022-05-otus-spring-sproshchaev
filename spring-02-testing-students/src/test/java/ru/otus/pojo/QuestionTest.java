package ru.otus.pojo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс QuestionTest содержит unit-тесты методов класса Question
 */
@DisplayName("Запуск тестов класса QuestionTest: ")
public class QuestionTest {

    /**
     * Метод successToString выполняет unit-тестирование метода toString
     */
    @DisplayName("тестирование метода toString")
    @Test
    public void successToString() {
        Question question = new Question(1, "An object is an instance:", "programs", "class", "method", 2);
        String resultExpected = "Question{questionNumber=1, questionText='An object is an instance:', " + "firstAnswer='programs', secondAnswer='class', thirdAnswer='method', rightAnswer=2}";
        assertThat(question.toString()).isEqualTo(resultExpected);
    }
}