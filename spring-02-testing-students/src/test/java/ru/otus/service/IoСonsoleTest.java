package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс IoСonsoleTest содержит unit-тесты класса IoСonsole
 */
@DisplayName("Запуск тестов класса IoСonsoleTest: ")
class IoСonsoleTest {

    @DisplayName("тестирование метода readInt")
    @Test
    void readIntTest() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        IoСonsole ioСonsole = new IoСonsole();
        assertThat(ioСonsole.readInt()).isEqualTo(1);
        System.setIn(sysInBackup);
    }

    @DisplayName("тестирование метода readString")
    @Test
    void readStringTest() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Любая_строка".getBytes());
        System.setIn(in);
        IoСonsole ioСonsole = new IoСonsole();
        assertThat(ioСonsole.readString()).isEqualTo("Любая_строка");
        System.setIn(sysInBackup);
    }

    @DisplayName("тестирование метода writeString")
    @Test
    void writeStringTest() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        IoСonsole ioСonsole = new IoСonsole();
        ioСonsole.writeString("Test writeString");
        assertThat(output.toString()).isEqualTo("Test writeString");
        System.setOut(null);
    }

    @DisplayName("тестирование метода writeLnString")
    @Test
    void writeLnStringTest() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        IoСonsole ioСonsole = new IoСonsole();
        ioСonsole.writeLnString("Test writeLnString");
        assertThat(output.toString()).isEqualTo("Test writeLnString\r\n");
        System.setOut(null);
    }
}