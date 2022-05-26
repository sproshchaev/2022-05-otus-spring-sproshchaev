package ru.otus.domain;

import junit.framework.TestCase;
import org.junit.Assert;

/**
 * Тестирование методов класса QuestionTest
 */
public class QuestionTest extends TestCase {

    public void testTestToString() {
        Question question = new Question(1, "An object is an instance:",
                "programs", "class", "method", 2);
        String resultExpected = "Question{questionNumber=1, questionText='An object is an instance:', " +
                "firstAnswer='programs', secondAnswer='class', thirdAnswer='method', rightAnswer=2}";
        Assert.assertEquals(question.toString(), resultExpected);
    }
}