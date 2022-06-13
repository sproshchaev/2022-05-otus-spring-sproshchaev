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

    /**
     * Метод IoСonsole_ReadInt_ExpectedResult_1 производит тестирование метода readInt
     */
    @DisplayName("тестирование метода readInt")
    @Test
    void IoСonsole_ReadInt_ExpectedResult_1() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        IoСonsole ioСonsole = new IoСonsole();
        assertThat(ioСonsole.readInt()).isEqualTo(1);
        System.setIn(sysInBackup);
    }

    /**
     * Метод IoСonsole_ReadString_ExpectedResult_AnyString выполняет тестирование метода readString
     */
    @DisplayName("тестирование метода readString")
    @Test
    void IoСonsole_ReadString_ExpectedResult_AnyString() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("AnyString".getBytes());
        System.setIn(in);
        IoСonsole ioСonsole = new IoСonsole();
        assertThat(ioСonsole.readString()).isEqualTo("AnyString");
        System.setIn(sysInBackup);
    }

    /**
     * Метод IoСonsole_WriteString_ExpectedResult_WriteString выполняет тестирование метода writeString
     */
    @DisplayName("тестирование метода writeString")
    @Test
    void IoСonsole_WriteString_ExpectedResult_WriteString() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        IoСonsole ioСonsole = new IoСonsole();
        ioСonsole.writeString("Test writeString");
        assertThat(output.toString()).isEqualTo("Test writeString");
        System.setOut(null);
    }

    /**
     * Метод IoСonsole_WriteLnString_ExpectedResult_WriteLnString выполняет тестирование метода writeLnString
     */
    @DisplayName("тестирование метода writeLnString")
    @Test
    void IoСonsole_WriteLnString_ExpectedResult_WriteLnString() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        IoСonsole ioСonsole = new IoСonsole();
        ioСonsole.writeLnString("Test writeLnString");
        assertThat(output.toString()).isEqualTo("Test writeLnString\r\n");
        System.setOut(null);
    }

}