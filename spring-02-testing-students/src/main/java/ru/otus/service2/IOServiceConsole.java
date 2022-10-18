package ru.otus.service2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceConsole implements IOService {

    private final PrintStream output;
    private final Scanner input;

    @Autowired
    public IOServiceConsole() {
        this.output = System.out;
        this.input = new Scanner(System.in);
    }

    @Override
    public int readInt() {
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public int readIntWithPrompt(String prompt) {
        outputString(prompt);
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        outputString(prompt);
        return input.nextLine();
    }

    @Override
    public void outputString(String text) {
        output.println(text);
    }
}
