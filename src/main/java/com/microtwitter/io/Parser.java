package com.microtwitter.io;

import java.util.Arrays;
import java.util.List;

import static com.microtwitter.io.Intent.*;

public class Parser {
    private final String DELIMITER = " ";
    private final int USER_OFFSET = 0;
    private final int INTENT_OFFSET = 1;
    private final int PAYLOAD_OFFSET = 2;
    private final String EMPTY_PAYLOAD = "";

    /**
     * Parses a command from natural language to a broken down structure.
     */
    public Command parse(String command) {
        String[] tokens = command.split(DELIMITER);
        String user = tokens[USER_OFFSET];
        if (isWallCommand(tokens)) {
            return wallCommand(user);
        }
        if (hasPayload(tokens)) {
            String intent = tokens[INTENT_OFFSET];
            String payload = getPayload(tokens);
            if (intent.equals(POST.inNaturalLanguage)) {
                return postCommand(user, payload);
            }
            if (intent.equals(FOLLOW.inNaturalLanguage)) {
                return followCommand(user, payload);
            }
        }
        return readCommand(user);
    }

    private boolean isWallCommand(String[] tokens) {
        return tokens.length == 2;
    }

    private boolean hasPayload(String[] tokens) {
        return tokens.length > PAYLOAD_OFFSET;
    }

    private String getPayload(String[] tokens) {
        List<String> payloadList = Arrays.asList(tokens).subList(PAYLOAD_OFFSET, tokens.length);
        return String.join(DELIMITER, payloadList);
    }

    private Command wallCommand(String user) {
        return new Command(user, Intent.WALL, EMPTY_PAYLOAD);
    }

    private Command postCommand(String user, String payload) {
        return new Command(user, Intent.POST, payload);
    }

    private Command followCommand(String user, String payload) {
        return new Command(user, Intent.FOLLOW, payload);
    }

    private Command readCommand(String user) {
        return new Command(user, Intent.READ, EMPTY_PAYLOAD);
    }
}
