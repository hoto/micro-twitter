package com.microtwitter.commands;

import com.microtwitter.io.Console;

public interface Command {
    void execute(Console out);
}
