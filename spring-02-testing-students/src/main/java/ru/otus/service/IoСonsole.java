package ru.otus.service;


import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Scanner;

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

    @Override
    public int readInt() {
        return Integer.parseInt(input.next());
    }

    @Override
    public String readString() {
        return input.nextLine();
    }

    @Override
    public void writeString(String string) {
        output.print(string);
    }

    @Override
    public void writeLnString(String string) {
        output.println(string);
    }
}
