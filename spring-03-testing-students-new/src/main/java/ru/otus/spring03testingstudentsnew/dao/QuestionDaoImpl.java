package ru.otus.spring03testingstudentsnew.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring03testingstudentsnew.pojo.Answer;
import ru.otus.spring03testingstudentsnew.pojo.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class QuestionDaoImpl implements QuestionDao {
    private final String fileCsvName;

    public QuestionDaoImpl(@Value("${fileCsvName}") String fileCsvName) {
        this.fileCsvName = fileCsvName;
    }

    @Override
    public List<Question> getQuestionList() {
        List<Question> questionsList = new ArrayList<>();
        Question currentQuestion;
        try (InputStream fileCsv = QuestionDaoImpl.class.getResourceAsStream("/" + fileCsvName);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(fileCsv)))) {
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
