package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.dao.ReadingQuestionsFile;

import java.io.IOException;
import java.util.Scanner;

/**
 * Приложение по проведению тестирования студентов - вывод вопросов и получение вариантов ответа
 * В ресурсах хранятся вопросы и различные ответы к ним в виде CSV файла (5 вопросов)
 */
public class Main {
    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        ReadingQuestionsFile readingQuestionsFile = context.getBean(ReadingQuestionsFile.class);

        Scanner in = new Scanner(System.in);
        System.out.println("Testing on the basics of Java");
        System.out.print("Please enter your name: ");
        String studentsName = in.nextLine();

        int answerId;
        boolean inputIsCorrect;
        int countCorrectAnswers = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.println(readingQuestionsFile.getQuestionById(i).getQuestionText());
            System.out.println(" 1) " + readingQuestionsFile.getQuestionById(i).getFirstAnswer());
            System.out.println(" 2) " + readingQuestionsFile.getQuestionById(i).getSecondAnswer());
            System.out.println(" 3) " + readingQuestionsFile.getQuestionById(i).getThirdAnswer());
            inputIsCorrect = false;

            while (!inputIsCorrect) {

                System.out.print("Enter 1, 2 or 3: ");
                answerId = in.nextInt();
                System.out.println("");

                if ((answerId == 1) || (answerId == 2) || (answerId == 3)) {
                    inputIsCorrect = true;
                    if (answerId == readingQuestionsFile.getQuestionById(i).getRightAnswer()) {
                        countCorrectAnswers++;
                    }
                } else {
                    System.out.print("Incorrect input, try again. ");
                }
            }
        }

        in.close();

        System.out.printf(studentsName + ", your result: " + countCorrectAnswers + " out of 5");

    }
}
