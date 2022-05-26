package ru.otus.dao;

import ru.otus.domain.Question;

import java.io.*;

/**
 * Класс ReadingQuestionsFile осуществляет чтение файла с вопросами, находящегося в папке \resources\questions.csv
 */
public class ReadingQuestionsFile {

    /**
     * Поле класса fileCsvName содержит имя файла \resources\questions.csv
     */
    private String fileCsvName;

    /**
     * Конструктор класса
     */
    public ReadingQuestionsFile(String fileName) {
        this.fileCsvName = fileName;
    }

    /**
     * Метод getQuestionById возвращает экземпляр класса Question
     *
     * @param questionId
     * @return
     * @throws IOException
     */
    public Question getQuestionById(int questionId) {

        File file = new File(ReadingQuestionsFile.class.getClassLoader().getResource(fileCsvName).getFile());

        int questionIdFromCsv = -1;
        String questionTextFromCsv = "";
        String firstAnswerFromCsv = "";
        String secondAnswerFromCsv = "";
        String thirdAnswerFromCsv = "";
        int rightAnswerFromCsv = -1;

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            String[] words;

            while ((line = br.readLine()) != null) {

                words = line.split(";");

                if ((isDigit(line.charAt(0))) && (Integer.parseInt(words[0]) == questionId)) {

                    questionIdFromCsv = Integer.parseInt(words[0]);
                    questionTextFromCsv = words[1];
                    firstAnswerFromCsv = words[2];
                    secondAnswerFromCsv = words[3];
                    thirdAnswerFromCsv = words[4];
                    rightAnswerFromCsv = Integer.parseInt(words[5]);

                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if (questionIdFromCsv != -1) {
            return new Question(questionIdFromCsv, questionTextFromCsv, firstAnswerFromCsv, secondAnswerFromCsv,
                    thirdAnswerFromCsv, rightAnswerFromCsv);
        } else {
            return null;
        }
    }

    /**
     * Метод isDigit осуществляет проверку символа, передаваемого в качестве аргумента,
     * на соответствие цифре 0..9
     *
     * @param charVar
     * @return
     */
    public boolean isDigit(char charVar) {

        if ((charVar == '0') || (charVar == '1') || (charVar == '2') || (charVar == '3') || (charVar == '4')
                || (charVar == '5') || (charVar == '6') || (charVar == '7') || (charVar == '8') || (charVar == '9')) {
            return true;
        } else {
            return false;
        }
    }

}
