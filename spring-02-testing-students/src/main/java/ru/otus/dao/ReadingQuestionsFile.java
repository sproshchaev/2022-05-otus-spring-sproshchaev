package ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.pojo.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс ReadingQuestionsFile осуществляет чтение файла с вопросами, находящегося в папке \resources\questions.csv
 */
@Component
public class ReadingQuestionsFile implements Reading {

    /**
     * Поле класса fileCsvName содержит имя файла \resources\questions.csv
     */
    private final String fileCsvName;
    /**
     * Поле класса questionList содержит список вопросов
     */
    private final List<Question> questionList;

    public ReadingQuestionsFile(@Value("${fileCsvName}") String fileCsvName) {
        this.fileCsvName = fileCsvName;
        this.questionList = fillQuestionList();
    }

    /**
     * Метод fillQuestionList читает из ресурса файл с вопросами
     *
     * @return
     */
    public List<Question> fillQuestionList() {
        List<Question> questionsList = new ArrayList<>();
        Question currentQuestion;
        File file = new File(ReadingQuestionsFile.class.getClassLoader().getResource(fileCsvName).getFile());
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                currentQuestion = readQuestionFromLine(line);
                if (currentQuestion != null) {
                    questionsList.add(currentQuestion);
                }
            }
            return questionsList;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод getQuestionById возвращает экземпляр класса Question с заданным questionId
     *
     * @param questionId
     * @return
     */
    @Override
    public Question getQuestionById(int questionId) {
        return questionList.get(questionId - 1);
    }

    /**
     * Метод readQuestionFromLine осуществляет преобразование экземпляра класса Question из передаваемой текстовой
     * строки в формате .csv
     *
     * @param line
     * @return
     */
    private Question readQuestionFromLine(String line) {
        List<String> listAnswer = new ArrayList<>();
        String[] words = line.split(";");
        if ((Character.isDigit(line.charAt(0)))) {
            for (int i = 2; i < words.length - 1; i++) {
                listAnswer.add(words[i]);
            }
            return new Question(Integer.parseInt(words[0]), words[1], listAnswer, Integer.parseInt(words[words.length - 1/*5*/]));
        } else return null;
    }
}
