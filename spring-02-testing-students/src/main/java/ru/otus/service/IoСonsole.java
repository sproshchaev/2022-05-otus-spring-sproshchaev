package ru.otus.service;

import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Класс IoСonsole осуществляет ввод текста в консоль, чтение данных из консоли
 */
@Component
public class IoСonsole implements IoService {
    private final PrintStream output;
    private final Scanner input;

    /**
     * Конструктор класса IoСonsole
     *
     * @param output
     * @param input
     */
    public IoСonsole() {
        this.output = System.out;
        this.input = new Scanner(System.in);
    }

    /**
     * Метод readInt считывает целочисленные значения из консоли
     *
     * @return
     */
    @Override
    public int readInt() {
        return Integer.parseInt(input.next());
    }

    /**
     * Метод readString считывает строковые значения из консоли
     *
     * @return
     */
    @Override
    public String readString() {
        return input.nextLine();
    }

    /**
     * Метод writeString выводит передаваемую строку в консоль
     *
     * @param string
     */
    @Override
    public void writeString(String string) {
        output.print(string);
    }

    /**
     * Метод writeLnString выводит передаваемую строку в консоль с последующим переводом строки
     *
     * @param string
     */
    @Override
    public void writeLnString(String string) {
        output.println(string);
    }

}
