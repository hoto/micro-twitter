package com.microtwitter;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;

public class MicroTwitter {
    private static Iterator<String> stdin = new Scanner(System.in);
    private static PrintStream stdout = System.out;

    public static void main(String... args) {
        new MicroTwitter().start();
    }

    private void start() {
        printHelp();
        loop();
    }

    private void printHelp() {
        stdout.println("Enter command (or exit to close):");
    }

    private void loop() {
        while (true) {
            String next = stdin.next();
            if (next.equals("exit")) break;
        }
    }

}
