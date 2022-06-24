package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.pojo.Student;

/**
 * Класс Identification
 */
@Service
public class Identification {
    private TextToConsole textToConsole;
    private ReadFromConsole readFromConsole;
    private Student student;

    @Autowired
    public Identification(TextToConsole textToConsole, ReadFromConsole readFromConsole, Student student) {
        this.textToConsole = textToConsole;
        this.readFromConsole = readFromConsole;
        this.student = student;
    }

    public void getStudentName() {
        textToConsole.doPrintWelcomeAndWaitGetYouName();
        student.setName(readFromConsole.readStudentsName());
    }

}
