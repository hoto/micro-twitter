package com.microtwitter.io;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private PrintStream stdout;

    public Console(Scanner scanner, PrintStream stdout) {
        this.scanner = scanner;
        this.stdout = stdout;
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void writeOutput(String text) {
        stdout.println(text);
    }
}
