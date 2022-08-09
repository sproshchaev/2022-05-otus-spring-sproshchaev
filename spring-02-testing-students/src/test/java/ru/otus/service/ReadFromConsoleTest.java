package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс ReadFromConsoleTest содержит методы тестирования класса ReadFromConsole
 */
@DisplayName("Запуск тестов класса ReadFromConsole: ")
class ReadFromConsoleTest {

    /**
     * success readStudentsName
     */
    @DisplayName("тестирование метода readStudentsName")
    @Test
    void successReadStudentsName() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Student's name".getBytes());
        System.setIn(in);
        ReadFromConsole readFromConsole = new ReadFromConsole();
        readFromConsole.setIoСonsole(new IoСonsole());
        assertThat(readFromConsole.readStudentsName()).isEqualTo("Student's name");
        System.setIn(sysInBackup);
    }
}