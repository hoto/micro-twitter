package com.microtwitter;

import com.microtwitter.commands.Command;
import com.microtwitter.commands.CommandFactory;
import com.microtwitter.io.Console;
import com.microtwitter.io.Parser;
import com.microtwitter.presenters.ConsoleMessagePresenter;
import com.microtwitter.presenters.MessagePresenter;
import com.microtwitter.time.Clock;
import com.microtwitter.time.SystemClock;
import com.microtwitter.users.UserRepository;

import java.util.Scanner;

public class MicroTwitter {
    private final Parser parser;
    private final Console console;

    public MicroTwitter(Clock clock, Console console) {
        UserRepository userRepository = new UserRepository(clock);
        MessagePresenter messagePresenter = new ConsoleMessagePresenter(clock);
        CommandFactory commandFactory = new CommandFactory(userRepository, messagePresenter);
        this.parser = new Parser(commandFactory);
        this.console = console;
    }

    public static void main(String... args) {
        Clock clock = new SystemClock();
        Scanner stdin = new Scanner(System.in);
        Console console = new Console(stdin, System.out);
        new MicroTwitter(clock, console).start();
    }

    public void start() {
        printHelp();
        loop();
    }

    private void printHelp() {
        console.writeOutput("Enter command (exit to close):");
    }

    private void loop() {
        while (true) {
            String line = console.getInput();
            if (line.equals("exit")) break;
            executeCommand(line);
        }
    }

    private void executeCommand(String line) {
        Command command = parser.parse(line);
        command.execute(console);
    }

}
