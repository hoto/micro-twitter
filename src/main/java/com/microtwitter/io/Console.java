package com.microtwitter.io;

import java.util.Scanner;

public class Console {
    private Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getInput() {
        return scanner.nextLine();
    }
}
