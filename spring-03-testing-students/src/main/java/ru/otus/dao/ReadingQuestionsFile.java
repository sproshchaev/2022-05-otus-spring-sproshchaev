package ru.otus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.pojo.Question;

import java.io.*;

/**
 * Класс ReadingQuestionsFile осуществляет чтение файла с вопросами, находящегося в папке \resources\questions.csv
 */
@Component
public class ReadingQuestionsFile implements ReadingFile {

    /**
     * Поле класса fileCsvName содержит имя файла \resources\questions.csv
     */
    //@Value("${fileCsvName}")
    private String fileCsvName;

    /**
     * Конструктор класса с параметрами
     * @param fileCsvName
     */
    @Autowired
    public ReadingQuestionsFile(@Value("${fileCsvName}") String fileCsvName) {
        this.fileCsvName = fileCsvName;
    }

    /**
     * Конструктор класса без параметров
     */
    public ReadingQuestionsFile() {
    }

    /**
     * Метод setFileCsvName
     *
     * @param fileCsvName
     */
    public void setFileCsvName(String fileCsvName) {
        this.fileCsvName = fileCsvName;
    }

    /**
     * Метод getQuestionById возвращает экземпляр класса Question с заданным questionId
     *
     * @param questionId
     * @return
     * @throws IOException
     */
    @Override
    public Question getQuestionById(int questionId) {
        Question currentQuestion = null;
        File file = new File(ReadingQuestionsFile.class.getClassLoader().getResource(fileCsvName).getFile());
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            boolean questionFound = false;
            while ((line = br.readLine()) != null) {
                currentQuestion = readQuestionFromLine(line);
                if ((currentQuestion != null) && (currentQuestion.getQuestionId() == questionId)) {
                    questionFound = true;
                    break;
                }
            }
            if (questionFound) {
                return currentQuestion;
            } else return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод readQuestionFromLine осуществляет преобразование экземпляра класса Question из передаваемой текстовой
     * строки в формате .csv
     *
     * @param line
     * @return
     */
    private Question readQuestionFromLine(String line) {
        String[] words = line.split(";");
        if ((Character.isDigit(line.charAt(0)))) {
            return new Question(Integer.parseInt(words[0]), words[1], words[2], words[3], words[4],
                    Integer.parseInt(words[5]));
        } else return null;
    }

}
