package ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.pojo.Answer;
import ru.otus.pojo.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class QuestionDaoImpl implements QuestionDao {
    private final String fileCsvName;
    private final List<Question> questionList;
    public QuestionDaoImpl(@Value("${fileCsvName}") String fileCsvName) {
        this.fileCsvName = fileCsvName;
        this.questionList = fillQuestionList();
    }

    @Override
    public List<Question> getQuestionList() {
        return questionList;
    }

    private List<Question> fillQuestionList() {
        List<Question> questionsList = new ArrayList<>();
        Question currentQuestion;
        File file = new File(Objects.requireNonNull(QuestionDaoImpl.class.getClassLoader().getResource(fileCsvName))
                .getFile());
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

    private Question readQuestionFromLine(String line) {
        List<Answer> listAnswer = new ArrayList<>();
        String[] words = line.split(";");
        int indexAnswer;
        if ((Character.isDigit(line.charAt(0)))) {
            indexAnswer = 0;
            for (int i = 2; i < words.length - 1; i++) {
                indexAnswer++;
                listAnswer.add(new Answer(indexAnswer, words[i],
                        (indexAnswer == Integer.parseInt(words[words.length - 1]))));
            }
            return new Question(Integer.parseInt(words[0]),
                    words[1],
                    listAnswer);
        } else return null;
    }
}
