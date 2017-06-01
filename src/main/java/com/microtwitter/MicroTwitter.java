package com.microtwitter;

import com.microtwitter.commands.Command;
import com.microtwitter.commands.CommandFactory;
import com.microtwitter.io.Parser;
import com.microtwitter.presenters.ConsoleMessagePresenter;
import com.microtwitter.presenters.MessagePresenter;
import com.microtwitter.time.Clock;
import com.microtwitter.time.SystemClock;
import com.microtwitter.users.UserRepository;

import java.io.PrintStream;
import java.util.Scanner;

public class MicroTwitter {
    private static Scanner stdin = new Scanner(System.in);
    private static PrintStream stdout = System.out;

    private final Clock clock = new SystemClock();
    private final UserRepository userRepository = new UserRepository(clock);
    private final MessagePresenter messagePresenter = new ConsoleMessagePresenter(clock);
    private final CommandFactory commandFactory = new CommandFactory(userRepository, messagePresenter);
    private final Parser parser = new Parser(commandFactory);

    public static void main(String... args) {
        new MicroTwitter().start();
    }

    private void start() {
        printHelp();
        loop();
    }

    private void printHelp() {
        stdout.println("Enter command (exit to close):");
    }

    private void loop() {
        while (true) {
            String line = stdin.nextLine();
            if (line.equals("exit")) break;
            executeCommand(line);
        }
    }

    private void executeCommand(String line) {
        Command command = parser.parse(line);
        command.execute(stdout);
    }

}
