package com.microtwitter.commands;

import java.io.PrintStream;

public interface Command {
    void execute(PrintStream out);
}
