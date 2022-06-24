package ru.otus.pojo;

/**
 * Класс Question - POJO-класс, содержащий поля вопроса из файла \resources\questions.csv
 */
public class Question {

    /**
     * Поле "Номер вопроса"
     */
    private int questionId;

    /**
     * Поле "Текст вопроса"
     */
    private String questionText;

    /**
     * Поле "Первый вариант ответа"
     */
    private String firstAnswer;

    /**
     * Поле "Второй вариант ответа"
     */
    private String secondAnswer;

    /**
     * Поле "Третий вариант ответа"
     */
    private String thirdAnswer;

    /**
     * Поле "Номер правильного варианта ответа"
     */
    private int rightAnswer;

    /**
     * Конструктор класса
     */
    public Question(int questionId, String questionText, String firstAnswer, String secondAnswer, String thirdAnswer,
                    int rightAnswer) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.rightAnswer = rightAnswer;
    }

    /**
     * Геттеры и сеттеры полей класса
     */
    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getFirstAnswer() {
        return firstAnswer;
    }

    public String getSecondAnswer() {
        return secondAnswer;
    }

    public String getThirdAnswer() {
        return thirdAnswer;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    /**
     * Метод toString() выводит знечения полей класса
     *
     * @return
     */
    @Override
    public String toString() {
        return "Question{" +
                "questionNumber=" + questionId +
                ", questionText='" + questionText + '\'' +
                ", firstAnswer='" + firstAnswer + '\'' +
                ", secondAnswer='" + secondAnswer + '\'' +
                ", thirdAnswer='" + thirdAnswer + '\'' +
                ", rightAnswer=" + rightAnswer +
                '}';
    }
}
