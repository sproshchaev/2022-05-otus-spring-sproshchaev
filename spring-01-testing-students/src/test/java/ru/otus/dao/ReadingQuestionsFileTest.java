package ru.otus.dao;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

/**
 * Тестирование методов класса ReadingQuestionsFile
 */
public class ReadingQuestionsFileTest extends TestCase {

    public void testGetQuestionById() {
        ReadingQuestionsFile readingQuestionsFile = new ReadingQuestionsFile("questions.csv");
        String resultExpected = "Question{questionNumber=1, questionText='An object is an instance:', firstAnswer='programs', secondAnswer='class', thirdAnswer='method', rightAnswer=2}";
        Assert.assertEquals(readingQuestionsFile.getQuestionById(1).toString(), resultExpected);
    }

    public void testFailGetQuestionById() {
        ReadingQuestionsFile readingQuestionsFile = new ReadingQuestionsFile("questions.csv");
        Assertions.assertThrows(NullPointerException.class, () -> readingQuestionsFile.getQuestionById(0).toString());
    }

    public void testIsDigit() {
        ReadingQuestionsFile readingQuestionsFile = new ReadingQuestionsFile("questions.csv");
        Assert.assertEquals(readingQuestionsFile.isDigit('1'), true);
        Assert.assertEquals(readingQuestionsFile.isDigit('A'), false);
    }

}