package com.microtwitter.io;

import com.microtwitter.commands.*;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final String DELIMITER = " ";
    private static final int USER_OFFSET = 0;
    private static final int INTENT_OFFSET = 1;
    private static final int PAYLOAD_OFFSET = 2;

    private final CommandFactory commandFactory = new CommandFactory();

    public Command parse2(String input) {
        String[] tokens = input.split(DELIMITER);
        String user = tokens[USER_OFFSET];
        String intent = tokens.length > 1 ? tokens[INTENT_OFFSET] : "";
        String payload = getPayload(tokens);
        return commandFactory.create(user, intent, payload);
    }

    private String getPayload(String[] tokens) {
        if (tokens.length < PAYLOAD_OFFSET) return "";
        List<String> payloadList = Arrays.asList(tokens).subList(PAYLOAD_OFFSET, tokens.length);
        return String.join(DELIMITER, payloadList);
    }
}
